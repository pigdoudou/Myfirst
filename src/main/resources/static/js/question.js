function postComment() {
    var questionId = $("#question_id").val();
    var content = $("#content").val();
    if (content == null || content == "") {
        alert("回复内容不能为空");
    }
    $.ajax({
        type: "post",
        url: "/comment",
        data: JSON.stringify({
            "parentId": questionId,
            "content": content,
            "type": 1
        }),
        success: function (response) {
            if (response.code == 2000) {
                window.location.reload();
            } else {
                if (response.code == 1002) {
                    var msg = confirm(response.message);
                    if (msg) {
                        window.open("https://github.com/login/oauth/authorize?client_id=80ae44270a5a5b72534f&redirect_uri=http://localhost:8080/callback&scope=user&state=1");
                        window.localStorage.setItem("close", "1");
                    }
                } else {
                    alert(response.message);
                }
            }
        },
        contentType: "application/json",
        dataType: "json"
    });
}

function collComments(e) {
    var id = e.getAttribute("data-id");
    var commentIcon=$("#commentIcon");
    var comments = $("#comment-" + id);
    if (comments.hasClass("in")) {
        comments.removeClass("in")
        commentIcon.removeClass("active");
        $(".boxs"+id).remove();
    } else {
        $.getJSON("/comment/"+id,function (data) {
            var subCommentContainer=$("#comment-"+id);
            $.each(data.data,function (index,comment) {
                var box=$("<div/>",{
                    "class":"boxs"+id
                })
                var media=$("<div/>",{
                    "class":"media",
                    "style":"margin-top: 20px;margin-left: 10px"
                })
                var mediaLeft=$("<div/>",{
                    "class":"media-left"
                });
                var img=$("<img/>",{
                    "class":"media-object img-rounded",
                    "width":"30",
                    "src":comment.user.avatarUrl
                });
                var mediaBody=$("<div/>",{
                    "class":"media-body"
                });
                var mediaTop=$("<div/>",{
                    "style":"margin-top: 3px"
                });
                var mediaHeading=$("<span/>",{
                    "class":"media-heading",
                    "style":"font-size: 15px;",
                    "text":comment.user.name
                });
                var content=$("<div/>",{
                    "style":"margin-top: 7px"
                });
                var contentSpan=$("<span/>",{
                    "text":comment.content
                });
                var date=$("<div/>",{
                    "class":"good"
                });
                var dateSpan=$("<span/>",{
                    "style":"float: right",
                    "html":moment(comment.gmtCreate).format('YYYY-MM-DD')
                });
                var hr=$("<hr/>",{
                    "class":"col-lg-12 col-md-12 col-sm-12",
                    "style":"margin-top: 5px;margin-bottom: 5px"
                })
                media.prepend(hr);
                date.prepend(dateSpan);
                mediaBody.prepend(date);
                content.prepend(contentSpan);
                mediaBody.prepend(content);
                mediaTop.prepend(mediaHeading);
                mediaBody.prepend(mediaTop);
                mediaLeft.prepend(img);
                media.prepend(mediaBody);
                media.prepend(mediaLeft);
                box.prepend(media);
                subCommentContainer.prepend(box);
            })
        })
        comments.addClass("in");
        commentIcon.addClass("active");
    }
}

function postReply(e) {
    var id=e.getAttribute("data-id");
    var content=$("#input-"+id).val();
    if (content == null || content == "") {
        alert("评论内容不能为空");
    }
    $.ajax({
        type: "post",
        url: "/reply",
        data: JSON.stringify({
            "parentId": id,
            "content": content,
            "type": 2
        }),
        success: function (response) {
            if (response.code == 2000) {
                window.location.reload();
            } else {
                if (response.code == 1002) {
                    var msg = confirm(response.message);
                    if (msg) {
                        window.open("https://github.com/login/oauth/authorize?client_id=80ae44270a5a5b72534f&redirect_uri=http://localhost:8080/callback&scope=user&state=1");
                        window.localStorage.setItem("close", "1");
                    }
                } else {
                    alert(response.message);
                }
            }
        },
        contentType: "application/json",
        dataType: "json"
    });
}


function selectTag(tagValue){
    var pre= $('#tag').val();
    if(pre.indexOf(tagValue)==-1){
    if(pre){
        $('#tag').val(pre+','+tagValue);
    }else{
        $('#tag').val(tagValue);
    }}else{
        $('#tag').val('');
    }
}