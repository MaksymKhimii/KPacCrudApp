<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<html>
<head>
	<title>Select Direct Reports</title>
	<link rel="STYLESHEET" type="text/css" href="../../include/dhtmlx.css">
	<script src="../../include/dhtmlx.js"></script>
	<meta charset="ISO-8859-1">
</head>
<body>
<p>Hello world!</p>
<%--<div>
	<form:form action="/rest/sortKPac" modelAttrimute="myform">
		Sort by:<br>
		<form:select path="sortingTitleArray">
			&lt;%&ndash;<form:option value="" label="--"> </form:option>&ndash;%&gt;
			<c:forEach var="s" items="${sort_select}">
				<form:option value="${s}" label="${s}"> </form:option>
			</c:forEach>
			&lt;%&ndash;<form:option each="s : ${sort_select}"
					th:value="${s}"
					th:text="${s}"/>&ndash;%&gt;
		</form:select>
		<a>Filter by:</a>
		<form:select path="${myform.filter}">
			<option value=""> --</option>
			<c:forEach var="f" items="${filter}">
				<form:option value="${f}">${f}</form:option>
			</c:forEach>
			&lt;%&ndash;<option th:each="f : ${filter}"
					th:value="${f}"
					th:text="${f}"/>&ndash;%&gt;

		</form:select>
		<button type="submit">Sort</button>
	</form:form>
</div>--%>
<a style="text-align: center" href="@{/kpac/createKpac}">Create</a>
<br>
<div id="gridbox" style="text-align: center; width:550px;height:700px;"></div>
<script>
	/* 	function doOnRowSelected(id){
             alert("Rows with id: "+id+" was selected by user")
         }

         function doOnChecked(row_id,cell_index,state) {
             console.log("Rows with id: " + row_id + " cell_index: " + cell_index + " state: " + state)
             if (state == true) {
                 mygrid.cellById(row_id, 7).setValue("Daniel Houston");    // are you getting from session???
             } else {
                 mygrid.cellById(row_id, 7).setValue("");
             }
         }*/
	function doOnRowSelected(id) {
		alert("Rows with id: " + id + " was selected by user")
	}

	mygrid = new dhtmlXGridObject('gridbox');
	mygrid.setImagePath("include/imgs/");
	mygrid.setHeader("ID,Title,Description,Creation Date");
	mygrid.setInitWidths("100,100,200,150");
	mygrid.setColAlign("left,left,left,left");
	mygrid.setColumnIds("id,title,description,date_of_creation");
	mygrid.setColTypes("ro,ed,ed,ed");
	mygrid.setColSorting("str,str,str,str");
	/* mygrid.attachEvent("onCheck", doOnChecked);*/
	mygrid.attachEvent("onRowSelect", doOnRowSelected);
	mygrid.init();

	mygrid.load("http://localhost:8080/kpacs", "js");
</script>
</body>
</html>
