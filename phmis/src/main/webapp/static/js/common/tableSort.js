/**
 * 为table的列添加排序功能，要排序的列需添加sort属性
 * el:<th class="center" sort='code' >编号</th>,code是order by 的值
 * @param {Object} tableId
 * @memberOf {TypeName} 
 */
function tableSort(tableId,formId,orderByValue) {
	//在form下添加一个隐藏元素
	/***/
	var orderByEl = '<input type="hidden" id="orderBy" name="orderBy" value="'+orderByValue+'">';
	$("#"+formId).append(orderByEl);
	

	//为表格添加排序功能，表格中需要排序的列需要加sort属性
	$("#" + tableId + " th").each(
			function(i, dom) {
				if ($(this).attr('sort') != undefined && $(this).attr('sort') != '' && $(this).attr('sort') != null) {
					//设置鼠标样式
					$(this).css( {cursor : "pointer"});
					$(this).attr("title", "点击排序");

					$(this).bind("click",function() {
										if ($("#orderBy").val() == '') {
											$("#orderBy").val(" order by "+ $(this).attr('sort')+ " desc ");
										} else if ($("#orderBy").val().indexOf("desc") >= 0) {
											$("#orderBy").val(" order by "+ $(this).attr('sort')+ " asc ");
										} else {
											$("#orderBy").val(" order by "+ $(this).attr('sort')+ " desc ");
										}
										var currentPage = $("li.active font:eq(0)").html();//找到当前第几页
										//alert(currentPage);
										nextPage(currentPage);//翻页方法，组装参数然后将form submit
									});
				}
			})
}