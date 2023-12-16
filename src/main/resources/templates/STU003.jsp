<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Insert title here</title>
    <link th:href="@{/css/style.css}" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>

</head>
<body>
<div id="testheader">
    <div class="container">
        <div class=row>
            <div class="col-md-5 ">
                <a href="MNU001.html"><h3>Student Registration</h3></a>
            </div>
            <div class="col-md-6">
                <p style="color:black"
                   th:text="${#session.getAttribute('role')} + ': ' + ${#session.getAttribute('name')} + ', ' + ${#session.getAttribute('userid')}"></p>
                <p id="currentDate">Current Date : YY.MM.DD</p>
            </div>

            <div class="col-md-1">
                <a class="btn-basic" id="lgnout-button" th:href='@{/}'>Log Out</a>
            </div>
        </div>
    </div>

</div>
<!-- <div id="testsidebar">Hello World </div> -->
<div class="container">
    <div class="sidenav">


        <a th:href="@{/course}">Course Registration </a>
        <a th:href="@{/studentseartch}">Student Search </a>
        <a th:href="@{/usermanage}">Users Management</a>
        <div th:each="data : ${userlist}">
            <div th:if="${data.userid == session.userid}">
                <a th:href="@{adminnewpwd(id=${data.userid})}">Admin Change Password</a>
            </div>
        </div>
    </div>
</div>
<div class="main_contents">
    <div id="sub_content">
        <form class="row g-3 mt-3 ms-2" action="search4" method="get">

            <div class="col-auto">
                <label for="inputPassword2" class="visually-hidden"></label>
                <input name="searchstudent" type="text" class="form-control" id="inputPassword2" placeholder="search">
            </div>

            <div class="col-auto">
                <button type="submit" class="btn btn-success mb-3">Search</button>
            </div>
            <div class="col-auto">
                <button type="reset" class="btn btn-secondary mb-3">Reset</button>
            </div>
        </form>
        <div>
            <table class="table table-striped" id="stduentTable">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Student ID</th>
                    <th scope="col">Name</th>
                    <th scope="col">Course Name</th>
                    <th scope="col">Details</th>
                </tr>
                </thead>
                <tbody>
                <tr th:each="data, status: ${list22}" th:object="${data}">
                    <th scope="row" th:text="${status.index +1}"></th>
                    <td th:text="${data.studentid}"></td>
                    <td th:text="${data.name}"></td>
                    <td th:text="${data.attend}"></td>
                    <td>
                        <a type="button" class="btn btn-success  " th:href='@{update(id=${data.studentid})}'>
                            View Details
                        </a>
                    </td>

                </tr>

                </tbody>
                <tbody>
                <tr th:each="data, status: ${list}" th:object="${data}">

                    <th scope="row" th:text="${status.index +1}"></th>
                    <td th:text="${data.studentid}"></td>
                    <td th:text="${data.name}"></td>
                    <td th:text="${data.attend}"></td>
                    <td>
                        <a type="button" class="btn btn-success  " th:href='@{update(id=${data.studentid})}'>
                            View Details
                        </a>
                    </td>

                </tr>

                </tbody>
            </table>
        </div>
    </div>
</div>
</div>

<div id="testfooter">
    <span>Copyright &#169; ACE Inspiration 2022</span>
</div>
<script>
    // Get the current date
    var currentDate = new Date();

    // Extract the year, month, and day
    var year = currentDate.getFullYear().toString().slice(-2); // Get the last 2 digits of the year
    var month = (currentDate.getMonth() + 1).toString().padStart(2, '0'); // Months are 0-based, so add 1 and pad with '0'
    var day = currentDate.getDate().toString().padStart(2, '0'); // Pad the day with '0'

    // Update the content of the <p> element
    document.getElementById("currentDate").textContent = "Current Date : " + year + "." + month + "." + day;
</script>

</body>
</html>