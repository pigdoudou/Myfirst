function postComment() {
    var questionId=$("#question_id").val();
    var content=$("#content").val();
    $.ajax({
        type: "post",
        url: "/comment",
        data:JSON.stringify({
            "parentId": questionId,
            "content": content,
            "type": 1
        }),
        success: function (response){
            if (response.code==2000){
                $("#comment_section").hide();
            }else{
                if(response.code==1002){
                    var msg=confirm(response.message);
                    if(msg){
                        window.open("https://github.com/login/oauth/authorize?client_id=80ae44270a5a5b72534f&redirect_uri=http://localhost:8080/callback&scope=user&state=1");
                        window.localStorage.setItem("close","1");
                    }
                }else{
                alert(response.message);}
            }
        },
        contentType: "application/json",
        dataType: "json"
    });
}