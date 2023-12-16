
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
<title>Insert title here</title>
    <link th:href="@{/css/style.css}" rel="stylesheet" />
        <link th:href="@{/css/style1.css}" rel="stylesheet" />
    
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
    
        <title>Course Registration</title>
        <style>
        </style>
</head>
<body>
  <div id="testheader">
        <div class="container">
            <div class=row>        
                <div class="col-md-5 ">
            <a href="MNU001.html"><h3>Student Registration</h3></a>
        </div>  
        <div class="col-md-6">
<p style="color:black" th:text="${#session.getAttribute('role')} + ': ' + ${#session.getAttribute('name')} + ', ' + ${#session.getAttribute('userid')}"></p>
<p id="currentDate">Current Date : YY.MM.DD</p>
        </div>  
        <div class="col-md-1" >
        <a  class="btn-basic" id="lgnout-button" th:href='@{/}'>Log Out</a>
        </div>        
    </div>
    </div>
    </div>
</div>
    <!-- <div id="testsidebar">Hello World </div> -->
    <div class="container">
   <div class="sidenav">
        
        
         <a th:href="@{addstudent}">Student Registration </a>
         <a th:href="@{studentseartch1}">Student Search </a>
<div th:each="data : ${list1}">
    <div th:if="${data.userid == session.userid}">
        <a th:href="@{newpwd(id=${data.userid})}">User Change Password</a>
        <a th:href="@{setupUpdatebook(id=${data.userid})}">User Update</a>
    </div>
</div>
    </div>
      <div class="main_contents">
          <div id="sub_content1">
          <form action="imageupload" method="post" th:object="${std1}" enctype="multipart/form-data">
                          <label for="studentID" class="studentphoto">Student Photo</label><br>
          			    <label for="file" id="file-label" class="file-label">
          
           <div th:each="photo : ${list}">
    <div th:if="${photo.studentid == #session.getAttribute('id')}">
        <img th:if="${photo.image == null}" th:src="@{/image/default_profile.jpg}" id="output">
        <img th:if="${photo.image != null}" th:src="@{/image/{imageName}(imageName=${photo.image})}" id="output">
    </div>
</div>

           	          <span class="change-text">Choose File</span>
           	
            <input type="hidden" th:field="*{studentid}" style="margin-left:50px;"/><br>
          <input type="file" th:field="*{image}" id="file" onchange="loadFile(event)" style="margin-left:30px;"/>
          </label>
          <button type="submit" style="margin-left:30px;" class="button button2">upload image</button>
          </form>
        
           </div>
    <div id="sub_content">
        <form action="studentup1" method="post" th:object="${std1}" enctype="multipart/form-data" > 		
            	
           
            <h2 class="col-md-6 offset-md-2 mb-5 mt-4">Student Registration</h2>
            
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="studentID" class="col-md-2 col-form-label">Student ID</label>
                <div class="col-md-4">
                    <input type="text" th:field="*{studentid}" class="form-control"  id="studentID" />
                </div>
            </div>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="name" class="col-md-2 col-form-label">Name</label>
                <div class="col-md-4">
                    <input type="text" class="form-control" id="name" th:field="*{name}" />
                </div>
            </div>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="dob" class="col-md-2 col-form-label">DOB</label>
                <div class="col-md-4">
                    <input type="date"   class="for-control" id="dob" th:field="*{dob}" th:value="${std1.dob}"/>
                </div>
                <div> </div>
            </div>
            <fieldset class="row mb-4">
                <div class="col-md-2"></div>
                <legend class="col-form-label col-md-2 pt-0">Gender</legend>
                <div class="col-md-4">
                    <div class="form-check-inline">
                        <input type="radio"  class="form-check-input" items="genderlist" th:field="*{gender}" name="gridRadios" id="gridRadios1" value="Male"/>
                        <label class="form-check-label" for="gridRadios1">
                            Male
                        </label>
                    </div>
                    <div class="form-check-inline">
                        <input type="radio"  class="form-check-input"  items="genderlist" th:field="*{gender}" name="gridRadios" id="gridRadios2" value="Female"/>
                        <label class="form-check-label" for="gridRadios2">
                            Female
                        </label>
                    </div>
    
                </div>
            </fieldset>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="phone" class="col-md-2 col-form-label">Phone</label>
                <div class="col-md-4">
                    <input type="text" th:field="*{phone}" class="form-control" id="phone" th:value="${std1.phone}"/>
                </div>
            </div>
            
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="education" class="col-md-2 col-form-label">Education</label>
                <div class="col-md-4">
                    <select class="form-select" th:field="*{education}" aria-label="Education" id="education">
   					 <option value="Bachelor of Information Technology" th:selected="${std1.education == 'Bachelor of Information Technology'}">Bachelor of Information Technology</option>
    				<option value="Diploma in IT" th:selected="${std1.education == 'Diploma in IT'}">Diploma in IT</option>
  				  <option value="Bachelor of Computer Science" th:selected="${std1.education == 'Bachelor of Computer Science'}">Bachelor of Computer Science</option>
				</select>

                </div>
            </div>
            <fieldset class="row mb-4">
                <div class="col-md-2"></div>
                <legend class="col-form-label col-md-2 pt-0">Attend</legend>
                
             <div class="col-md-4">
   
    <div th:each="course : ${courses}">
        <div class="form-check-inline col-md-5">
            <input type="checkbox" class="form-check-input" name="attend" th:id="'gridRadios2_' + ${course.coursename}" 
                   th:value="${course.coursename}" th:checked="${sl.contains(course.coursename)}">
            <label class="form-check-Label" th:for="'gridRadios2_' + ${course.coursename}" th:text="${course.coursename}"></label>
        </div>
    </div>
</div> 
            </fieldset>				
                           <div class="">
            </div>
                
    		<div class="row mb-4">
                <div class="col-md-4"></div>
    
                <div class="col-md-4">
                    <button type="submit" class="btn btn-secondary ">
    
                        <span>Update</span>
                    </button>
                    <a  style="background-color:green;" class="btn btn-danger" th:href='@{studentseartch1}'>
                        <span>Back</span>
                    </a>
                    
                </div>

    
            </div>
            </form>
    </div>
</div>
</div>
        <div id="testfooter">
            <span>Copyright &#169; ACE Inspiration 2022</span>
        </div>
        <script>
            /* Loop through all dropdown buttons to toggle between hiding and showing its dropdown content - This allows the user to have multiple dropdowns without any conflict */
            var dropdown = document.getElementsByClassName("dropdown-btn");
            var i;
            
            for (i = 0; i < dropdown.length; i++) {
              dropdown[i].addEventListener("click", function() {
              this.classList.toggle("active");
              var dropdownContent = this.nextElementSibling;
              if (dropdownContent.style.display === "block") {
              dropdownContent.style.display = "none";
              } else {
              dropdownContent.style.display = "block";
              }
              });
            }
            </script>
            <script>
var loadFile = function (event) {
	  var image = document.getElementById("output");
	  image.src = URL.createObjectURL(event.target.files[0]);
	};
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