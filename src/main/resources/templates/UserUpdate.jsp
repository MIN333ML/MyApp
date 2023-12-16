
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
<title>Insert title here</title>
    <link th:href="@{/css/style.css}" rel="stylesheet" />
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
            <title>Course Registration</title>
</head>
<body>
 <div id="testheader">
        <div class="container">
            <div class=row>        
                <div class="col-md-5 ">
            <a href="MNU001.html"><h3>Student Registration</h3></a>
        </div>  
                <div class="col-md-6">
<p th:text="${#session.getAttribute('role')} + ': ' + ${#session.getAttribute('name')} + ', ' + ${#session.getAttribute('userid')}"></p>
<p id="currentDate">Current Date : YY.MM.DD</p>
        </div>  
        <div class="col-md-1" >
            <a  class="btn-basic" id="lgnout-button"  th:href='@{/}'>Log Out</a>
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
                <div th:each="data : ${list}">
                <div th:if="${data.userid == session.userid}">
              	<a th:href="@{adminnewpwd(id=${data.userid})}">Admin Change Password</a>
                </div>
                </div>       
        </div>
      </div>
      <div class="main_contents">
    <div id="sub_content">
        <form action="updatebook1" method="post" th:object="${upda1}" enctype="multipart/form-data" onsubmit="return validateForm()">

        <h2 class="col-md-6 offset-md-2 mb-5 mt-4">User</h2>
                          <div class="row mb-4">
   
         <div class="col-md-2"></div>
                <div class="col-md-4">
                    <input type="hidden" class="form-control" id="name1" th:field="*{userid}"  value="${upda1.userid}" disabled/>                    
                </div>
                            <div id="error" style="color: red"></div>
                
            </div>
                  <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="name" class="col-md-2 col-form-label">Name  :</label>
                <div class="col-md-4">
					<span th:text="${upda1.name}"></span>
                    <span id="error" style="color:red;"></span>                     
                    
                </div>                
            </div>
            
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="email" class="col-md-2 col-form-label">Email  :</label>
                <div class="col-md-4">
                    <span th:text="${upda1.email}"></span>
                    <span id="error1" style="color:red;"></span>                     
                    
                </div>
                            <div id="error1" style="color: red"></div>
                
            </div>
            
                        
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="userRole" class="col-md-2 col-form-label">User Role :</label>
                <div class="col-md-4">
                     <span th:text="${upda1.role}"></span>					    
                </div>
            </div>
        <div class="row mb-4">
            <div class="col-md-4"></div>

            <div class="col-md-6">
                
            <a class="btn btn-secondary col-md-2 " th:href='@{usermanage}'>
                Back
            </a>
    

        </div>
        </div>
        </form>
    </div>
</div>
        <div id="testfooter">
            <span>Copyright &#169; ACE Inspiration 2022</span>
        </div>
      <script>
function validateForm() {
    // Get the form elements
    var name = document.getElementById("name1").value;
    var email = document.getElementById("email1").value;
    var password = document.getElementById("password1").value;
    var confirmPassword = document.getElementById("confirmPassword1").value;

    // Reset error messages
    
    // Validation rules
    var isValid = true;
    clearError("error");
    clearError("error1");
    clearError("error2");
    clearError("error3");


        if (name === "") {
            document.getElementById("error").innerHTML = "Name is required";
            isValid= false;
        } 
    
    if (email === "") {
        document.getElementById("error1").innerHTML = "Email is required";
        isValid = false;
    }

    if (password === "") {
        document.getElementById("error2").innerHTML = "Password is required";
        isValid = false;
    }

    if (confirmPassword === "") {
        document.getElementById("error3").innerHTML = "Confirm Password is required";
        isValid = false;
    } else if (password !== confirmPassword) {
        document.getElementById("error3").innerHTML = "Passwords do not match";
        isValid = false;
    }

    return isValid;
}

function clearError(errorId) {
    document.getElementById(errorId).innerHTML = "";
}
</script>
<script>
function validateName() {
    var name = document.getElementById("name1").value;
    
    if (name === "") {
        document.getElementById("error").innerHTML = "Name is required";
    }
}
function validateEmail() {
    var email = document.getElementById("email1").value;
    
    if (name === "") {
        document.getElementById("error1").innerHTML = "Email is required";
    }
}
function validatePassword() {
    var password1 = document.getElementById("password1").value;
    
    if (password1 === "") {
        document.getElementById("error2").innerHTML = "Password is required";
    }
}
function validateComfirmPwd() {
    var comfrimPassword = document.getElementById("confirmPassword1").value;
    
    if (confirmPassword === "") {
        document.getElementById("error3").innerHTML = "Confirm Password is required";
    } else if (password !== confirmPassword) {
        document.getElementById("error3").innerHTML = "Passwords do not match";
    }


}


</script>       
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