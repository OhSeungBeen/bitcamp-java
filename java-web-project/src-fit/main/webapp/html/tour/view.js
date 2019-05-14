var param = location.href.split('?')[1],
    tourNo = param.split('=')[1],
    templateSrc = $('#comment-template').html(),
    trGenerator = Handlebars.compile(templateSrc),
    pageNo = 1;

if(sessionStorage.getItem('loginUser')){
  var user = JSON.parse(sessionStorage.getItem('loginUser'))
}

if (param) {
    $('h1').html('투어 조회');
    tourList(tourNo);
    commentList(tourNo, pageNo);
}


// TourList
function tourList(tourNo) {
  
  $.getJSON('../../app/json/tour/detail?no=' + tourNo,
      function(obj) {
    $('#title').val(obj.tour.title);
    $('#subHeading').val(obj.tour.subHeading);
    $('#content').val(obj.tour.content);
    $('#totalHour').val(obj.tour.totalHour);
    $('#hashTag').val(obj.tour.hashTag);
    $('#personnel').val(obj.tour.personnel);
    $('#transportation').val(obj.tour.transportation);
    $('#price').val(obj.tour.price);
    $('#photoname').val(obj.tour.tourPhoto[0].name);
    $('#photpath').val(obj.tour.tourPhoto[0].path);
    $('#theme').val(obj.tour.theme[0].theme);
  });
}

// CommentList
function commentList(tourNo, pageNo) {
  
  $.getJSON('../../app/json/tourcomment/list?tourNo=' + tourNo + '&pageNo=' + pageNo,
      function(obj) {
    $(trGenerator(obj)).appendTo($('#comment'));
    showUpdateDeleteButton();
    commentAmountUpdate(obj.commentAmount, true);
    $(document.body).trigger('addEventAddButton');
    $(document.body).trigger('addEventUpdateDetailButton');
    checkMoreComment();
  });
}

//Show Update,Delete Button
function showUpdateDeleteButton() {

  if(user == undefined) 
    return;

  var memberNameNodes = $('.bit-member-name');
  for (memberNameNode of memberNameNodes) {

  if(user.name == $(memberNameNode).html()) {
    $(memberNameNode).parent().next().children().show();
      giveDeleteUdpateBtnId();
    }
  }
};

//Give Update,Delete Button Id
function giveDeleteUdpateBtnId() {

  var deleteButtons = $('.bit-comment-delete-btn');
  for(deleteButton of deleteButtons) {
    if ($(deleteButton).attr('id')) 
      continue;

    var commentNoNode = $(deleteButton).parent().prev().prev().children().first();
    $(deleteButton).attr('id', 'delete' + commentNoNode.val());

  }

  var updateButtons = $('.bit-comment-update-btn');
  for(updateButton of updateButtons) {
    if ($(updateButton).attr('id')) 
      continue;

    var commentNoNode = $(updateButton).parent().prev().prev().children().first();
    $(updateButton).attr('id', 'update' + commentNoNode.val());

  }
}

//CommentAmount Update
function commentAmountUpdate(commentAmount, init) {
  
  if(init){
    $('#commentAmount').html('댓글수' + commentAmount);
    
  }else {    
    var UpdatedCommentAmount = Number($('#commentAmount').html().replace(/[^0-9]/g,"")) + commentAmount;
    $('#commentAmount').html
    ('댓글수' + UpdatedCommentAmount);
  }
}

//AddEvent Comment Add Button
$(document.body).bind('addEventAddButton', () => {
  $('#comment-add-button').off().click((e) => {
    e.preventDefault();
    $.post('../../app/json/tourcomment/add',
      {
        tourNo : tourNo,
        memberNo : user.no,
        parentNo : 0,
        level : 0,
        content : $('#comment-add').val()
      }, 
      function(obj) {
        if (obj.status == 'success') {
          
          var newComment ={
            'tourComment' : [{
            'no': obj.no,
            'content': $('#comment-add').val(),
            'createdDate' : now_yyyy_mm_dd_hh_mm(),
            'member' : {name: user.name}
            }]
          };
          
          $(trGenerator(newComment)).prependTo($('#comment'));
          showUpdateDeleteButton();
          $(document.body).trigger('addEventUpdateDetailButton');
          commentAmountUpdate(1, false);
          $('#comment-add').val('');
          checkMoreComment();
          } else {
            alert('등록 실패입니다!\n' + obj.message)
          }
    });
  });
});


//AddEvent Comment Update,Delete Button
$(document.body).bind('addEventUpdateDetailButton', () => {
  
  //AddEvent Comment Delete Button
  $('.bit-comment-delete-btn').off().click((e) => {
    e.preventDefault();
    var commentNo = $(e.target).attr('id').replace(/[^0-9]/g,"");
    $.getJSON('../../app/json/tourcomment/delete?no=' + commentNo,
        function(obj) {
          if (obj.status == 'success') {
            $(e.target).parent().parent().remove();
            commentAmountUpdate(-1);
          } else {
            alert('삭제 실패입니다!\n' + obj.message)
          }
        });
  });

  //AddEvent Comment Update Button
  $('.bit-comment-update-btn').off().click((e) => {
    
    e.preventDefault();
    var contentNode = $(e.target).parent().next().children(),
    preContentValue = contentNode.val();
    contentNode.removeAttr("readonly")
    contentNode.focus();
    $(e.target).hide();
    $(e.target).next().hide();
    $(e.target).parent().append('<a href="#" >저장</a>');
    $(e.target).parent().append('<a href="#" >취소</a>');

    //AddEvent Comment Update Cancle
    $($(e.target).next().next().next()).off().click((e)=>{
      
      e.preventDefault();
      $(e.target).prev().prev().show();
      $(e.target).prev().prev().prev().show();
      $(e.target).prev().remove()
      $(e.target).remove();
      contentNode.attr("readonly",'true')
      contentNode.val(preContentValue);
    })

    //AddEvent Comment Update Save
    $($(e.target).next().next()).off().click((e)=>{
      
      e.preventDefault();
      $.post('../../app/json/tourcomment/update',
          {
            no : $(e.target).prev().attr('id').replace(/[^0-9]/g,""),
            content : contentNode.val()
          }, 
          function(obj) {
            if (obj.status == 'success') {
              contentNode.attr("readonly",'true')
              $(e.target).prev().prev().show();
              $(e.target).prev().show();
              $(e.target).next().remove();
              $(e.target).remove();
            } else {
              alert('수정 실패입니다!\n' + obj.message)
            }
          });
    });
  })
});


//CheckMoreComment
function checkMoreComment() {
  
  var currentPageCommentAmount = $('.commentRow').length,
      allCommentAmout = Number($('#commentAmount').html().replace(/[^0-9]/g,""));
      
      if(allCommentAmout > currentPageCommentAmount){
        $('#more-comment-btn').show();
        $(document.body).trigger('addEventMoreCommentButton');
      } else {
        $('#more-comment-btn').hide();
      }
}

//AddEventMoreCommentButton
$(document.body).bind('addEventMoreCommentButton', () => {

  $('#more-comment-btn').off().click((e)=>{
    e.preventDefault();
    pageNo++;
    commentList(tourNo, pageNo);
  });
});


//$(document.body).bind('addEventRecommentListButton', () => {
//  $('#bit-recomment-list-btn').off().click((e)=>{
//    e.preventDefault();
//    pageNo++;
//    var no = param.split('=')[1];
//    
//    console.log(pageNo);
//    $.getJSON('../../app/json/tour/detail?no=' + no + '&pageNo=' + pageNo + '&addDeleteCount=' + addDeleteCount,
//        function(obj) {
//      $(trGenerator(obj)).appendTo(comment);
//      showUpdateDeleteButton();
//      
//      if(pageNo >= window.totalPage){
//        $('#next-comment-btn').hide();
//      }
//      $(document.body).trigger('addEventUpdateDetailButton');
//    });
//  });
//});

//function showReCommentAddButton() {
//  
//  if(user == undefined){
//    alert('로그인이 되어있지 않습니다.');
//    location.href = "/bitcamp-fit-tour/html/auth/login.html";
//  }
//
//}


//Print current time yyyy_mm_dd_hh_mm format
function now_yyyy_mm_dd_hh_mm () {
  now = new Date();
  year = "" + now.getFullYear();
  month = "" + (now.getMonth() + 1); if (month.length == 1) { month = "0" + month; }
  day = "" + now.getDate(); if (day.length == 1) { day = "0" + day; }
  hour = "" + now.getHours(); if (hour.length == 1) { hour = "0" + hour; }
  minute = "" + now.getMinutes(); if (minute.length == 1) { minute = "0" + minute; }
  return year + "-" + month + "-" + day + " " + hour + ":" + minute;
}

