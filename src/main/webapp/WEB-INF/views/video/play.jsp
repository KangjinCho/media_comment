<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- bootstrap -->
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="http://code.jquery.com/jquery-2.1.1.min.js"
            type="text/javascript"></script>
    <script src="/resources/bootstrap/js/bootstrap.min.js"></script>
    <!-- favicon-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>영상 업로드</title>
    <style>
        .col-centered {
            float: none;
            margin: 0 auto;
        }

        .player {
            max-width: 100%
        }

        .main_frame {
            margin-top: 100px;
        }

        .divide {
            border-bottom: 1px solid #ddd;
        }

        .img_div {
            max-width: 100%;
        }

        .like {
            margin: 3px;
        }

        .preview_img {
            width: 100%;
        }

        #reply_content {
            margin: 10px;
        }

        .panel {
            margin-top: 5px;
        }

        iframe {
            max-width: 100%;
        }

        .row {
            margin: 2px;
        }

        hr {
            margin: auto;
        }

        #tag {
            color: #2a803d;
        }
        #float_reply{
            font-weight:bold;

        }
    </style>
</head>
<body style="background-color:#9da6a9">
<div class="col-md-6 col-md-offset-3 main_frame">
    <div class="panel panel-default">
        <div class="panel-body row">
            <span id="float_reply"></span>
        </div>
    </div>

    ${videoVO.video_code}

    <div class="panel panel-default">
        <div class="panel-body row">
            <span id="tag">#Ed Sheeran #뮤비 #pop</span>
            <h1>${videoVO.video_title}</h1>
            <h3>${videoVO.user_id}</h3>
            <div class="pull-right">
                <span>  ${videoVO.video_hit} &nbsp;
                    좋아요 3,321 개</span>

            </div>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-body row">
            <form method="post" action="/video/reply" id="frm_reply">
                <h4><input type="text" name="user_id" placeholder="유저아이디 (숫자)"/></h4>
                <span id="p_time"></span>&nbsp;&nbsp;
                <input type="hidden" id="reply_playtime" name="reply_playtime"/>
                <input type="hidden" name="video_id" value="${videoVO.video_id}">
                <button type="button" class="btn btn-default" onclick="refresh()"><span
                        class="glyphicon glyphicon-refresh" aria-hidden="true"></span></button>
    
                <textarea class="form-control" rows="3" id="reply_content" name="reply_content"></textarea>
                <div class="pull-right">
                    <button type="submit" class="btn btn-primary">등록</button>
                </div>
            </form>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-body">


            <c:forEach var="reply" items="${list}">
            <div class="div_reply row">
                <h4>${reply.user_id}</h4>
                <a onclick="seek(${reply.reply_playtime})">${reply.reply_playtime}</a>
                <p> ${reply.reply_content}</p>
                <div class="pull-right">
                    <button type="button" class="btn btn-default" id="thumb_up"><i class="fa fa-thumbs-o-up"></i></button>
                    <button type="button" class="btn btn-default" id="thumb_down"><i class="fa fa-thumbs-o-down"></i></button>
                </div>
            </div>
            <hr>
            </c:forEach>
        </div>
    </div>


    <script>
        $(function () {
            console.log("document ready");
            var src=document.getElementById('player').src;
            src+='?enablejsapi=1';
            document.getElementById('player').src=src;
            console.log("player : "+src);
        })
        
        var player;
        //api ready
        function onYouTubeIframeAPIReady() {
            player = new YT.Player('player');
            var offset = $("#player").offset();
            $("#float_reply").offset = ({left: offset.left + 200, top: offset.top + 100});
            $("#float_reply").hide();


            //리플 띄울시간 계산 (예제)
//            setInterval(function () {
//                var time = Math.floor(player.getCurrentTime());
//                if (time == 68) {
//                    $("#float_reply").text("아프겠다 ㅋㅋㅋㅋ");
//                    showReply();
//                } else if (time == 200) {
//                    $("#float_reply").text("!!! 혐 주의 !!!");
//                    showReply();
//                } else if (time == 120) {
//                    $("#float_reply").text("뭐하는거죠?");
//                    showReply();
//                }
//
//            }, 100);
        }

        //댓글 입력 플래그
        var flag = 0;

        //현재 재생시간 리프레시
        function refresh() {
            var time = player.getCurrentTime();
            $("#p_time").text(Math.floor(time));
        }

        //댓글 입력시 현재 재생시간 띄우기
        $("#reply_content").keydown(function () {
            var time = player.getCurrentTime();
            if (flag == 0) {
                $("#p_time").text(Math.floor(time));
                flag = 1;
            }
        });


        //리플 5초간 띄우기
        function showReply() {
            $("#float_reply").show();
            setTimeout(function () {
                $("#float_reply").hide();
            }, 5000);
        }

        //재생시간 이동
        function seek(time) {
            var offset = $("#player").offset();
            player.seekTo(time - 1, true);
            player.pauseVideo();
            $('html, body').animate({scrollTop: offset.top - 60}, 400);
        }
        
        //리플 등록
        $("#frm_reply").submit(function(){
            var playtime=$("#p_time").text();
            $("#reply_playtime").val(playtime);
            return true;
        });

        //추천
        $('#thumb_up').on('click',function(){

            //ajax

        });
        //비추
        $('#thumb_down').on('click',function(){

            //ajax

        });
    </script>
    <script src="https://www.youtube.com/iframe_api"></script>

</div>
</body>
</html>