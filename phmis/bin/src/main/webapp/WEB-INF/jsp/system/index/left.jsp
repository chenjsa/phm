﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="sidebar" class="sidebar                  responsive                    ace-save-state">
				<script type="text/javascript">
					try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
					//try{ace.settings.loadState('main-container')}catch(e){}
					
				</script>
 

			 

				<div class="sidebar-shortcuts" id="sidebar-shortcuts">
					<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
						<button class="btn btn-success">
							<i class="ace-icon fa fa-signal"></i>
						</button>

						<button class="btn btn-info">
							<i class="ace-icon fa fa-pencil"></i>
						</button>

						<button class="btn btn-warning">
							<i class="ace-icon fa fa-users"></i>
						</button>

						<button class="btn btn-danger">
							<i class="ace-icon fa fa-cogs"></i>
						</button>
					</div>

					<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
						<span class="btn btn-success"></span>

						<span class="btn btn-info"></span>

						<span class="btn btn-warning"></span>

						<span class="btn btn-danger"></span>
					</div>
				</div><!-- /.sidebar-shortcuts -->

				
					 	<ul class="nav nav-list">
				<li class="">
						<a href="main/index">
							<i class="menu-icon fa fa-desktop"></i>
							<span class="menu-text"> 首页 </span>
						</a>

						<b class="arrow"></b>
					</li>
				

				<c:forEach items="${menuList}" var="menu1">
				<c:if test="${menu1.hasMenu && '1' == menu1.isUse}">
					<li class=""  id="lm${menu1.id }">
						<a style="cursor:pointer;" class="dropdown-toggle">
							<i class="${menu1.menuIcon == null ? 'menu-icon fa fa-leaf black' : menu1.menuIcon}"></i>
							<span class="menu-text">
								${menu1.menuName }
							</span>
							<c:if test="${'[]' != menu1.subMenu}"><b class="arrow fa fa-angle-down"></b></c:if>
						</a>
						<b class="arrow"></b>
						<c:if test="${'[]' != menu1.subMenu}">
						<ul class="submenu">
						<c:forEach items="${menu1.subMenu}" var="menu2">
							<c:if test="${menu2.hasMenu && '1' == menu2.isUse}">
							<li class="" id="z${menu2.id }"> 
								<a style="cursor:pointer;" <c:if test="${not empty menu2.actionPath && '#' != menu2.actionPath}">target="mainFrame" onclick="siMenu('z${menu2.id }','lm${menu1.id }','${menu2.menuName }','${menu2.actionPath }')"</c:if><c:if test="${'[]' != menu2.subMenu}"> class="dropdown-toggle"</c:if>>
									<i class="${menu2.menuIcon == null ? 'menu-icon fa fa-leaf black' : menu2.menuIcon}"></i>
										${menu2.menuName }
									<c:if test="${'[]' != menu2.subMenu}"><b class="arrow fa fa-angle-down"></b></c:if>
								</a>
								<b class="arrow"></b>
								<c:if test="${'[]' != menu2.subMenu}">
								<ul class="submenu">
									<c:forEach items="${menu2.subMenu}" var="menu3">
									<c:if test="${menu3.hasMenu && '1' == menu3.isUse}">
										<li class="" id="m${menu3.id }">
											<a style="cursor:pointer;" <c:if test="${not empty menu3.actionPath && '#' != menu3.actionPath}">target="mainFrame" onclick="siMenu('m${menu3.id }','z${menu2.id }','${menu3.menuName }','${menu3.actionPath }')"</c:if><c:if test="${'[]' != menu3.subMenu}"> class="dropdown-toggle"</c:if>>
												<i class="${menu3.menuIcon == null ? 'menu-icon fa fa-leaf black' : menu3.menuIcon}"></i>
													${menu3.menuName }
												<c:if test="${'[]' != menu3.subMenu}"><b class="arrow fa fa-angle-down"></b></c:if>
											</a>
											<b class="arrow"></b>
	
											<c:if test="${'[]' != menu3.subMenu}">
											<ul class="submenu">
												<c:forEach items="${menu3.subMenu}" var="menu4">
												<c:if test="${menu4.hasMenu && '1' == menu4.isUse}">
												<li class="" id="n${menu4.id }">
													<c:if test="${'[]' != menu4.subMenu}">
													<a style="cursor:pointer;" target="mainFrame" target="mainFrame" onclick="siMenu('n${menu4.id }','m${menu3.id }','${menu4.menuName }','menu/otherlistMenu.do?id=${menu4.id }')">
													</c:if>
													<c:if test="${'[]' == menu4.subMenu}">
													<a style="cursor:pointer;" target="mainFrame" <c:if test="${not empty menu4.actionPath && '#' != menu4.actionPath}">target="mainFrame" onclick="siMenu('n${menu4.id }','m${menu3.id }','${menu4.menuName }','${menu4.actionPath }')"</c:if>>
													</c:if>
														<i class="${menu4.menuIcon == null ? 'menu-icon fa fa-leaf black' : menu4.menuIcon}"></i>
														${menu4.menuName }
													</a>
													<b class="arrow"></b>
												</li>
												</c:if>
												</c:forEach>
											</ul>
											</c:if>
										</li>
										</c:if>
									</c:forEach>
								</ul>
								</c:if>
							</li>
							</c:if>
						</c:forEach>
						</ul>
						</c:if>
					</li>
				</c:if>
				</c:forEach>

				</ul><!-- /.nav-list -->
				
				<!-- #section:basics/sidebar.layout.minimize -->
				<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
					<i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
				</div>
			
				<!-- /section:basics/sidebar.layout.minimize -->
				<script type="text/javascript">
					try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
				</script>
			</div>