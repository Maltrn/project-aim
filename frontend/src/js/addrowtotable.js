function addRow() {
	var table = document.getElementById("extendableTable");
	var length = document.getElementById("extendableTable").rows.length;
	// if row max reached?
	var row = table.insertRow(length);
	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);
	var cell3 = row.insertCell(2);
	cell1.innerHTML = "<input>";
	cell2.innerHTML = "<input>";
    cell3.innerHTML = '<td><input class="btn btn-danger" type="button" value="Delete" onclick="deleteRow(this)"></td>';

}


function deleteRow(r) {
    var i = r.parentNode.parentNode.rowIndex;
    document.getElementById("extendableTable").deleteRow(i);
}
