/*
 * Ext JS Library 2.0.2
 * Copyright(c) 2006-2008, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */

Ext.Button=Ext.extend(Ext.Component,{hidden:false,disabled:false,pressed:false,enableToggle:false,menuAlign:"tl-bl?",type:"button",menuClassTarget:"tr",clickEvent:"click",handleMouseEvents:true,tooltipType:"qtip",buttonSelector:"button:first",initComponent:function(){Ext.Button.superclass.initComponent.call(this);this.addEvents("click","toggle","mouseover","mouseout","menushow","menuhide","menutriggerover","menutriggerout");if(this.menu){this.menu=Ext.menu.MenuMgr.get(this.menu)}if(typeof this.toggleGroup==="string"){this.enableToggle=true}},onRender:function(C,A){if(!this.template){if(!Ext.Button.buttonTemplate){Ext.Button.buttonTemplate=new Ext.Template("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"x-btn-wrap\"><tbody><tr>","<td class=\"x-btn-left\"><i>&#160;</i></td><td class=\"x-btn-center\"><em unselectable=\"on\"><button class=\"x-btn-text\" type=\"{1}\">{0}</button></em></td><td class=\"x-btn-right\"><i>&#160;</i></td>","</tr></tbody></table>")}this.template=Ext.Button.buttonTemplate}var B,E=[this.text||"&#160;",this.type];if(A){B=this.template.insertBefore(A,E,true)}else{B=this.template.append(C,E,true)}var D=B.child(this.buttonSelector);D.on("focus",this.onFocus,this);D.on("blur",this.onBlur,this);this.initButtonEl(B,D);if(this.menu){this.el.child(this.menuClassTarget).addClass("x-btn-with-menu")}Ext.ButtonToggleMgr.register(this)},initButtonEl:function(B,C){this.el=B;B.addClass("x-btn");if(this.icon){C.setStyle("background-image","url("+this.icon+")")}if(this.iconCls){C.addClass(this.iconCls);if(!this.cls){B.addClass(this.text?"x-btn-text-icon":"x-btn-icon")}}if(this.tabIndex!==undefined){C.dom.tabIndex=this.tabIndex}if(this.tooltip){if(typeof this.tooltip=="object"){Ext.QuickTips.register(Ext.apply({target:C.id},this.tooltip))}else{C.dom[this.tooltipType]=this.tooltip}}if(this.pressed){this.el.addClass("x-btn-pressed")}if(this.handleMouseEvents){B.on("mouseover",this.onMouseOver,this);B.on("mousedown",this.onMouseDown,this)}if(this.menu){this.menu.on("show",this.onMenuShow,this);this.menu.on("hide",this.onMenuHide,this)}if(this.id){this.el.dom.id=this.el.id=this.id}if(this.repeat){var A=new Ext.util.ClickRepeater(B,typeof this.repeat=="object"?this.repeat:{});A.on("click",this.onClick,this)}B.on(this.clickEvent,this.onClick,this)},afterRender:function(){Ext.Button.superclass.afterRender.call(this);if(Ext.isIE6){this.autoWidth.defer(1,this)}else{this.autoWidth()}},setIconClass:function(A){if(this.el){this.el.child(this.buttonSelector).replaceClass(this.iconCls,A)}this.iconCls=A},beforeDestroy:function(){if(this.rendered){var A=this.el.child(this.buttonSelector);if(A){A.removeAllListeners()}}if(this.menu){Ext.destroy(this.menu)}},onDestroy:function(){if(this.rendered){Ext.ButtonToggleMgr.unregister(this)}},autoWidth:function(){if(this.el){this.el.setWidth("auto");if(Ext.isIE7&&Ext.isStrict){var A=this.el.child(this.buttonSelector);if(A&&A.getWidth()>20){A.clip();A.setWidth(Ext.util.TextMetrics.measure(A,this.text).width+A.getFrameWidth("lr"))}}if(this.minWidth){if(this.el.getWidth()<this.minWidth){this.el.setWidth(this.minWidth)}}}},setHandler:function(B,A){this.handler=B;this.scope=A},setText:function(A){this.text=A;if(this.el){this.el.child("td.x-btn-center "+this.buttonSelector).update(A)}this.autoWidth()},getText:function(){return this.text},toggle:function(A){A=A===undefined?!this.pressed:A;if(A!=this.pressed){if(A){this.el.addClass("x-btn-pressed");this.pressed=true;this.fireEvent("toggle",this,true)}else{this.el.removeClass("x-btn-pressed");this.pressed=false;this.fireEvent("toggle",this,false)}if(this.toggleHandler){this.toggleHandler.call(this.scope||this,this,A)}}},focus:function(){this.el.child(this.buttonSelector).focus()},onDisable:function(){if(this.el){if(!Ext.isIE6||!this.text){this.el.addClass(this.disabledClass)}this.el.dom.disabled=true}this.disabled=true},onEnable:function(){if(this.el){if(!Ext.isIE6||!this.text){this.el.removeClass(this.disabledClass)}this.el.dom.disabled=false}this.disabled=false},showMenu:function(){if(this.menu){this.menu.show(this.el,this.menuAlign)}return this},hideMenu:function(){if(this.menu){this.menu.hide()}return this},hasVisibleMenu:function(){return this.menu&&this.menu.isVisible()},onClick:function(A){if(A){A.preventDefault()}if(A.button!=0){return }if(!this.disabled){if(this.enableToggle&&(this.allowDepress!==false||!this.pressed)){this.toggle()}if(this.menu&&!this.menu.isVisible()&&!this.ignoreNextClick){this.showMenu()}this.fireEvent("click",this,A);if(this.handler){this.handler.call(this.scope||this,this,A)}}},isMenuTriggerOver:function(B,A){return this.menu&&!A},isMenuTriggerOut:function(B,A){return this.menu&&!A},onMouseOver:function(B){if(!this.disabled){var A=B.within(this.el,true);if(!A){this.el.addClass("x-btn-over");Ext.getDoc().on("mouseover",this.monitorMouseOver,this);this.fireEvent("mouseover",this,B)}if(this.isMenuTriggerOver(B,A)){this.fireEvent("menutriggerover",this,this.menu,B)}}},monitorMouseOver:function(A){if(A.target!=this.el.dom&&!A.within(this.el)){Ext.getDoc().un("mouseover",this.monitorMouseOver,this);this.onMouseOut(A)}},onMouseOut:function(B){var A=B.within(this.el)&&B.target!=this.el.dom;this.el.removeClass("x-btn-over");this.fireEvent("mouseout",this,B);if(this.isMenuTriggerOut(B,A)){this.fireEvent("menutriggerout",this,this.menu,B)}},onFocus:function(A){if(!this.disabled){this.el.addClass("x-btn-focus")}},onBlur:function(A){this.el.removeClass("x-btn-focus")},getClickEl:function(B,A){return this.el},onMouseDown:function(A){if(!this.disabled&&A.button==0){this.getClickEl(A).addClass("x-btn-click");Ext.getDoc().on("mouseup",this.onMouseUp,this)}},onMouseUp:function(A){if(A.button==0){this.getClickEl(A,true).removeClass("x-btn-click");Ext.getDoc().un("mouseup",this.onMouseUp,this)}},onMenuShow:function(A){this.ignoreNextClick=0;this.el.addClass("x-btn-menu-active");this.fireEvent("menushow",this,this.menu)},onMenuHide:function(A){this.el.removeClass("x-btn-menu-active");this.ignoreNextClick=this.restoreClick.defer(250,this);this.fireEvent("menuhide",this,this.menu)},restoreClick:function(){this.ignoreNextClick=0}});Ext.reg("button",Ext.Button);Ext.ButtonToggleMgr=function(){var A={};function B(E,G){if(G){var F=A[E.toggleGroup];for(var D=0,C=F.length;D<C;D++){if(F[D]!=E){F[D].toggle(false)}}}}return{register:function(C){if(!C.toggleGroup){return }var D=A[C.toggleGroup];if(!D){D=A[C.toggleGroup]=[]}D.push(C);C.on("toggle",B)},unregister:function(C){if(!C.toggleGroup){return }var D=A[C.toggleGroup];if(D){D.remove(C);C.un("toggle",B)}}}}();
Ext.SplitButton=Ext.extend(Ext.Button,{arrowSelector:"button:last",initComponent:function(){Ext.SplitButton.superclass.initComponent.call(this);this.addEvents("arrowclick")},onRender:function(D,A){var B=new Ext.Template("<table cellspacing=\"0\" class=\"x-btn-menu-wrap x-btn\"><tr><td>","<table cellspacing=\"0\" class=\"x-btn-wrap x-btn-menu-text-wrap\"><tbody>","<tr><td class=\"x-btn-left\"><i>&#160;</i></td><td class=\"x-btn-center\"><button class=\"x-btn-text\" type=\"{1}\">{0}</button></td></tr>","</tbody></table></td><td>","<table cellspacing=\"0\" class=\"x-btn-wrap x-btn-menu-arrow-wrap\"><tbody>","<tr><td class=\"x-btn-center\"><button class=\"x-btn-menu-arrow-el\" type=\"button\">&#160;</button></td><td class=\"x-btn-right\"><i>&#160;</i></td></tr>","</tbody></table></td></tr></table>");var C,F=[this.text||"&#160;",this.type];if(A){C=B.insertBefore(A,F,true)}else{C=B.append(D,F,true)}var E=C.child(this.buttonSelector);this.initButtonEl(C,E);this.arrowBtnTable=C.child("table:last");if(this.arrowTooltip){C.child(this.arrowSelector).dom[this.tooltipType]=this.arrowTooltip}},autoWidth:function(){if(this.el){var C=this.el.child("table:first");var B=this.el.child("table:last");this.el.setWidth("auto");C.setWidth("auto");if(Ext.isIE7&&Ext.isStrict){var A=this.el.child(this.buttonSelector);if(A&&A.getWidth()>20){A.clip();A.setWidth(Ext.util.TextMetrics.measure(A,this.text).width+A.getFrameWidth("lr"))}}if(this.minWidth){if((C.getWidth()+B.getWidth())<this.minWidth){C.setWidth(this.minWidth-B.getWidth())}}this.el.setWidth(C.getWidth()+B.getWidth())}},setArrowHandler:function(B,A){this.arrowHandler=B;this.scope=A},onClick:function(A){A.preventDefault();if(!this.disabled){if(A.getTarget(".x-btn-menu-arrow-wrap")){if(this.menu&&!this.menu.isVisible()&&!this.ignoreNextClick){this.showMenu()}this.fireEvent("arrowclick",this,A);if(this.arrowHandler){this.arrowHandler.call(this.scope||this,this,A)}}else{if(this.enableToggle){this.toggle()}this.fireEvent("click",this,A);if(this.handler){this.handler.call(this.scope||this,this,A)}}}},getClickEl:function(B,A){if(!A){return(this.lastClickEl=B.getTarget("table",10,true))}return this.lastClickEl},onDisable:function(){if(this.el){if(!Ext.isIE6){this.el.addClass("x-item-disabled")}this.el.child(this.buttonSelector).dom.disabled=true;this.el.child(this.arrowSelector).dom.disabled=true}this.disabled=true},onEnable:function(){if(this.el){if(!Ext.isIE6){this.el.removeClass("x-item-disabled")}this.el.child(this.buttonSelector).dom.disabled=false;this.el.child(this.arrowSelector).dom.disabled=false}this.disabled=false},isMenuTriggerOver:function(A){return this.menu&&A.within(this.arrowBtnTable)&&!A.within(this.arrowBtnTable,true)},isMenuTriggerOut:function(B,A){return this.menu&&!B.within(this.arrowBtnTable)},onDestroy:function(){Ext.destroy(this.arrowBtnTable);Ext.SplitButton.superclass.onDestroy.call(this)}});Ext.MenuButton=Ext.SplitButton;Ext.reg("splitbutton",Ext.SplitButton);
