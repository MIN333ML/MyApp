
    	
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
<title>Insert title here</title>
  <link href="/resources/css/style.css" rel="stylesheet">
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
            <p>User: USR001 Harry</p>
<p id="currentDate">Current Date : YY.MM.DD</p>
        </div>  
        <div class="col-md-1" >
            <input type="button" class="btn-basic" id="lgnout-button" value="Log Out" onclick="location.href='/codetest2/'">
        </div>        
    </div>
</div>

</div>
    <!-- <div id="testsidebar">Hello World </div> -->
    <div class="container">
    <div class="sidenav">
        
        <button class="dropdown-btn" > Class Management <i class="fa fa-caret-down"></i></button>
        
            <div class="dropdown-container">
          <a href="/codetest2/course">Course Registration </a>
          <a href="/codetest2/addstudent">Student Registration </a>
          <a href="/codetest2/studentseartch">Student Search </a>
        </div>
        <a href="/codetest2/usermanage">Users Management</a>
      </div>
      <div class="main_contents">
                      <div><h2 style="color:red;font-weight: bold">${error}</h2></div>  
      
    <div id="sub_content">
        <form:form class="row g-3 mt-3 ms-2" action="/codetest2/search3" method="get">
          
            <div class="col-auto">
                <label for="inputPassword2" class="visually-hidden">User Name</label>
                <input type="text"  name="searchstudent" class="form-control" id="inputPassword2" placeholder="User Name">
            </div>
 			 <div class="col-auto">
                <label for="inputPassword2" class="visually-hidden">Course Name</label>
                <input type="text"  name="searchstudent" class="form-control" id="inputPassword2" placeholder="Course Name">
            </div>
            <div class="col-auto">
                <button type="submit" class="btn btn-primary mb-3">Search</button>
            </div>
            <div class="col-auto">
                <button type="button" class="btn btn-secondary " onclick="location.href = '/codetest2/studentregister';">
                    Add
                </button>
    
            </div>
            <div class="col-auto">
                <button type="submit" class="btn btn-danger mb-3">Reset</button>
            </div>
        </form:form>
    
        <table class="table table-striped" id="stduentTable">
            <thead>
                <tr>
                    
                    <th scope="col">User ID</th>
                    <th scope="col">User Name</th>
                    <th scope="col">Details</th>
                    
                </tr>
            </thead>
            <c:forEach items="${list22}" var="data" varStatus="loop">
            <tbody>
                <tr>
    				
                    
                    <td>${loop.index + 1 }</td>
                    <td>${data.name}</td>
                    
                <td>
                    <button type="button" class="btn btn-success  " onclick="location.href = 'setupUpdatebook/${data.userid}';">
                        Update
                    </button>
                </td>
                <td><button type="submit" class="btn btn-secondary mb-3" data-bs-toggle="modal"  onclick="location.href = 'deletebook/${data.name}';"
                    data-bs-target="#exampleModal">Delete</button></td>
    
                </tr>
    			</c:forEach>
    			
            </tbody> 
        </table>
    
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Student Deletion</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        
                         <h5 style="color: rgb(127, 209, 131);">Are you sure want to delete !</h5>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-success col-md-2" data-bs-dismiss="modal">Ok</button>
    
                    </div>
                </div>
            </div>
        </div>
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
</body>
</html>