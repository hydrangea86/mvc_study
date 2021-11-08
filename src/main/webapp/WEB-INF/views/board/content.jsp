<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <%@ include file="../include/static-head.jsp" %>

    <style>
        .content-container {
            width: 60%;
            margin: 150px auto;
            position: relative;
        }

        .content-container .main-title {
            font-size: 24px;
            font-weight: 700;
            text-align: center;
            border-bottom: 2px solid rgb(75, 73, 73);
            padding: 0 20px 15px;
            width: fit-content;
            margin: 20px auto 30px;
        }

        .content-container .main-content {
            border: 2px solid #ccc;
            border-radius: 20px;
            padding: 10px 25px;
            font-size: 1.1em;
            text-align: justify;
            min-height: 400px;
        }

        .content-container .custom-btn-group {
            position: absolute;
            bottom: -10%;
            left: 50%;
            transform: translateX(-50%);
        }
    </style>
</head>

<body>
    <div class="wrap">
        <%@ include file="../include/header.jsp" %>

        <div class="content-container">

            <h1 class="main-title">${b.boardNo}번 게시물</h1>

            <div class="mb-3">
                <label for="exampleFormControlInput1" class="form-label">작성자</label>
                <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="이름" name="writer"
                    value="${b.writer}" disabled>
            </div>
            <div class="mb-3">
                <label for="exampleFormControlInput2" class="form-label">글제목</label>
                <input type="text" class="form-control" id="exampleFormControlInput2" placeholder="제목" name="title"
                    value="${b.title}" disabled>
            </div>
            <div class="mb-3">
                <label for="exampleFormControlTextarea1" class="form-label">내용</label>

                <p class="main-content">
                    ${b.content}
                </p>
            </div>

            <div class="btn-group btn-group-lg custom-btn-group button-list" role="group">
                <button type="button" class="btn btn-warning">
                    <a class="update-btn" href="/board/modify">수정</a>
                </button>
                <button type="button" class="btn btn-danger">
                    <a class="del-btn" href="/board/delete?boardNo=${b.boardNo}">삭제</a>
                </button>
                <button id="go-list" type="button" class="btn btn-dark">목록</button>
            </div>

        </div>

        <%@ include file="../include/footer.jsp" %>

    </div>
    <script>
        const $ul = document.querySelector('.button-list');

        $ul.addEventListener('click', e => {
            if (!e.target.matches('a.del-btn')) return;

            e.preventDefault();
            //console.log('클릭이벤트 발동!');

            if (confirm('정말로 삭제하시겠습니까?')) {
                //삭제 진행                
                location.href = e.target.getAttribute('href');
            } else {
                //삭제 취소
                return;
            }

        });

        //홈화면으로 버튼 이벤트
        const $homeBtn = document.getElementById('go-list');
        $homeBtn.onclick = e => {
            location.href = '/board/list';
        };
    </script>

</body>

</html>