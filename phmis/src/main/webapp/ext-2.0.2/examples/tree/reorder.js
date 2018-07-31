/*
 * Ext JS Library 2.0.2
 * Copyright(c) 2006-2008, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */

Ext.onReady(function(){
    // shorthand
    var Tree = Ext.tree;
    
 var l_jscd='';  
        // Ê÷ÐÎÅäÖÃ¿ªÊ¼  
           var loader = new Ext.tree.TreeLoader({
								dataUrl :¡°http://localhost:8080/Seal_com/extJsTree!getSonNode.action?jscd="
										+ l_jscd,
								listeners : {
									beforeload : function(loader,
											node, response) {
										l_fid = node.attributes.id;
									    //alert('\u6d4b\u8bd5:'+l_fid);
										var pp = {
											jscd : l_fid,
											jscd1 : l_fid
										}
										// alert();
										loader.baseParams = pp
									},
									load : function(This, node,
											response) {

										Ext.MessageBox.hide();

									}
								}
							});
    var tree = new Tree.TreePanel({
        el:'tree-div',
        useArrows:true,
        autoScroll:true,
        animate:true,
        enableDD:true,
        containerScroll: true, 
        loader: loader
    });

    // set the root node
    var root = new Tree.AsyncTreeNode({
        text: 'Ext JS',
        draggable:false,
        id:'source'
    });
    tree.setRootNode(root);

    // render the tree
    tree.render();
    root.expand();
});