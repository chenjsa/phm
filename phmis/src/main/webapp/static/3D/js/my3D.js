var bizBoxList='',basePath='';
//中间隔板，底板，顶板的长度
//var topBottomBroadLength=(('${cabinetLength}'*100)/3)/100+parseInt('${maxBoxPosition}')*2;
var topBottomBroadLengt;
var boxWidth=260;
var boxDepth=450;
var cabinetHeight=(50+65+100+135+210)/3+4*10;
var cabinetThickness=4;//机柜面板厚度
var cabinetBroadClearance=3;//面板间歇

var clock = new THREE.Clock();
var keyboard = new THREEx.KeyboardState();

var cabinetBottom,maxBoxPosition;



function initData(bizBoxListArg,basePathArg,cabinetLengthArg,maxBoxPositionArg){
	bizBoxList=bizBoxListArg;
	basePath=basePathArg;
	//alert("basePath:"+basePath);
	maxBoxPosition=maxBoxPositionArg;
	
	topBottomBroadLength=((cabinetLengthArg*100)/3)/100+parseInt(maxBoxPositionArg)*2;
	
	//机柜底部，拿出来方便箱子位置计算
	cabinetBottom = {
		x:boxDepth/3,y:cabinetThickness,z:topBottomBroadLength,
		name:'柜子底板',
		positionX:-150,positionY:0,positionZ:0,
		id:'cabinetBottom',
		topWall:{color:0x777777},
	};
}
function createBox(cube,scene,targetList){
	
	//var bizBoxList='${bizBoxListJson}';
	//console.log("bizBoxList:"+bizBoxList);
	
	var bizBoxListJson=JSON.parse(bizBoxList); 
	//console.log(bizBoxListJson.length);
	//console.log();
	//console.log((parseInt('${cabinetLength}')/3)/2-260/3-15);
	
	//找到A-1-1的pootionZ坐标：
	var A11PositionZ=cabinetBottom.positionZ+(topBottomBroadLength/2)-(boxWidth/6);
	//A-1-1的positionY的坐标
	var A11PositionY=cabinetBottom.positionY+(210/6);
	//alert("A11PositionZ:"+A11PositionZ);
	
	$.each(bizBoxListJson, function(i, item){
		if(item.position.substring(0,3)=='A-1'){//最底层
			var cubeObject1 = {
				//预设追底层的箱子高度是210
				x:boxDepth/3,y:210/3,z:boxWidth/3,
				//posotionX,在底板的位置上加一个值
				positionX:cabinetBottom.positionX+3,
				positionY:A11PositionY,//positionZ:('${cabinetLength}'/3)/2-260/3-15,
				id:item.id,
				name:item.code,
				//frontWall:{color:0XEF214A}
				frontWall:{img:basePath+'/static/3D/images/jins.jpg'},
				topWall:{img:basePath+'/static/3D/images/jins.jpg'},
			};
			//console.log("item.position:"+item.position);
			for(var i=0;i<maxBoxPosition;i++){
				var positionArray=item.position.split("-");
				if((i+1)==positionArray[positionArray.length-1]){
					cubeObject1.positionZ=A11PositionZ-parseInt(i)*(boxWidth/3)-(i*2);
				}
			}
			//console.log("位置："+item.position+"。 位置转为坐标，cubeObject1.positionZ:"+cubeObject1.positionZ);
			createCubeGeometry(cubeObject1,cube,scene,targetList);
			
			//创建中间隔板
			var middleBroad = {
				x:boxDepth/3,y:cabinetThickness,z:topBottomBroadLength,
				positionX:cabinetBottom.positionX+3,
				positionY:A11PositionY+(210/6)+cabinetBroadClearance+(cabinetThickness/2),
				positionZ:cabinetBottom.positionZ,
				id:'123',
				name:'向上第一个隔板',
				topWall:{color:0X050505},
				bottomWall:{color:0X050505},
				frontWall:{color:0X050505},
			};
			createCubeGeometry(middleBroad,cube,scene);
		}
		
		if(item.position.substring(0,3)=='A-2'){//向上算第二层
			var cubeObject1 = {
				x:boxDepth/3,y:135/3,z:boxWidth/3,
				positionX:cabinetBottom.positionX+3,
				positionY:A11PositionY+(210/6)+cabinetBroadClearance+(cabinetThickness)+135/6,
				//positionZ:('${cabinetLength}'/3)/2-260/3-15,
				id:item.id,
				name:item.code,
				frontWall:{color:0XEF214A}
			};
			for(var i=0;i<maxBoxPosition;i++){
				var positionArray=item.position.split("-");
				if((i+1)==positionArray[positionArray.length-1]){
					cubeObject1.positionZ=A11PositionZ-parseInt(i)*(boxWidth/3)-(i*2);
				}
			}
			//console.log("位置："+item.position+"。 位置转为坐标，cubeObject1.positionZ:"+cubeObject1.positionZ);
			createCubeGeometry(cubeObject1,cube,scene,targetList);
			
			//创建中间隔板
			var middleBroad = {
				//很薄的一个板，Y为3
				x:boxDepth/3,y:cabinetThickness,z:topBottomBroadLength,
				//这里与他保存一致; positionY,下层箱子的位置，加箱子一半的高度，加间距
				positionX:cabinetBottom.positionX+3,
				positionY:cubeObject1.positionY+(135/6)+cabinetBroadClearance+(cabinetThickness/2),
				positionZ:cabinetBottom.positionZ,
				id:'1233',
				name:'向上第二个隔板',
				topWall:{color:0X050505},
				bottomWall:{color:0X050505},
				frontWall:{color:0X050505},
			};
			createCubeGeometry(middleBroad,cube,scene);
		}
		
		//向上算第三层
		if(item.position.substring(0,3)=='A-3'){
			var cubeObject1 = {
				x:boxDepth/3,y:100/3,z:boxWidth/3,
				positionX:cabinetBottom.positionX+3,
				positionY:A11PositionY+(210/6)+135/3+cabinetBroadClearance*4+cabinetThickness*2+10,
				//positionZ:('${cabinetLength}'/3)/2-260/3-15,
				id:item.id,
				name:item.code,
				frontWall:{color:0XEF214A}
			};
			for(var i=0;i<maxBoxPosition;i++){
				var positionArray=item.position.split("-");
				if((i+1)==positionArray[positionArray.length-1]){
					cubeObject1.positionZ=A11PositionZ-parseInt(i)*(boxWidth/3)-(i*2);
				}
			}
			//console.log("位置："+item.position+"。 位置转为坐标，cubeObject1.positionZ:"+cubeObject1.positionZ);
			createCubeGeometry(cubeObject1,cube,scene,targetList);
			
			//创建中间隔板
			var middleBroad = {
				x:boxDepth/3,y:cabinetThickness,z:topBottomBroadLength,
				positionX:cabinetBottom.positionX+3,
				positionY:cubeObject1.positionY+(100/6)+cabinetBroadClearance+(cabinetThickness/2),
				positionZ:cabinetBottom.positionZ,
				id:'1233',
				name:'向上第二个隔板',
				topWall:{color:0X050505},
				bottomWall:{color:0X050505},
				frontWall:{color:0X050505},
			};
			createCubeGeometry(middleBroad,cube,scene);
		}
		
		
		//向上算第四层
		if(item.position.substring(0,3)=='A-4'){
			var cubeObject1 = {
				x:boxDepth/3,y:65/3,z:boxWidth/3,
				positionX:cabinetBottom.positionX+3,
				positionY:A11PositionY+(210/6)+135/3+100/3+cabinetBroadClearance*6+cabinetThickness*3,
				//positionZ:('${cabinetLength}'/3)/2-260/3-15,
				id:item.id,
				name:item.code,
				frontWall:{color:0XEF214A}
			};
			for(var i=0;i<maxBoxPosition;i++){
				var positionArray=item.position.split("-");
				if((i+1)==positionArray[positionArray.length-1]){
					cubeObject1.positionZ=A11PositionZ-parseInt(i)*(boxWidth/3)-(i*2);
				}
			}
			//console.log("位置："+item.position+"。 位置转为坐标，cubeObject1.positionZ:"+cubeObject1.positionZ);
			//createCubeGeometry(cubeObject1,cube,scene,targetList);
			
			//创建中间隔板
			var middleBroad = {
				x:boxDepth/3,y:cabinetThickness,z:topBottomBroadLength,
				positionX:cabinetBottom.positionX+3,
				positionY:cubeObject1.positionY+(65/6)+cabinetBroadClearance+(cabinetThickness/2),
				positionZ:cabinetBottom.positionZ,
				id:'1233',
				name:'向上第4个隔板',
				topWall:{color:0X050505},
				bottomWall:{color:0X050505},
				frontWall:{color:0X050505},
			};
			createCubeGeometry(middleBroad,cube,scene);
		}
		
		//向上算第五层
		if(item.position.substring(0,3)=='A-5'){
			var cubeObject1 = {
				x:boxDepth/3,y:50/3,z:boxWidth/3,
				positionX:cabinetBottom.positionX+3,
				positionY:A11PositionY+(210/6)+135/3+100/3+65/3+cabinetBroadClearance*8+cabinetThickness*4,
				//positionZ:('${cabinetLength}'/3)/2-260/3-15,
				id:item.id,
				name:item.code,
				frontWall:{color:0XEF214A}
			};
			for(var i=0;i<maxBoxPosition;i++){
				var positionArray=item.position.split("-");
				if((i+1)==positionArray[positionArray.length-1]){
					cubeObject1.positionZ=A11PositionZ-parseInt(i)*(boxWidth/3)-(i*2);
				}
			}
			//console.log("位置："+item.position+"。 位置转为坐标，cubeObject1.positionZ:"+cubeObject1.positionZ);
			createCubeGeometry(cubeObject1,cube,scene,targetList);
			//alert(A11PositionY);
		}
	});
}






/**创建矩形体
 * 参数实例：x:50,y:50,z:50,positionX:-100,positionY:50,positionZ:155,id:'4028b9815c76f191015c771253c70002'
 * 			name:'名称',frontWall:{color:0xDDDDDD,opacity:0.5,img:'a.jpg'},backWall:{},rightWall:{},leftWall:{},topWall:{},buttomWall:{}
 * @param {Object} cubeObject 对象参数
 * @param {Object} cube
 * @param {Object} scene
 * @param {Object} targetList
 */
function createCubeGeometry(cubeObject,cube,scene,targetList){
	var neheTexture = new THREE.ImageUtils.loadTexture(basePath+"/static/3D/images/a.jpg");
	/**正方形**/
	var cubeMaterialArray = [];
	
	//正面
	if(cubeObject.frontWall!=undefined){
		cubeMaterialArray.push( new THREE.MeshBasicMaterial(
			{ 	color: cubeObject.frontWall.color==undefined?'':cubeObject.frontWall.color,
				opacity:cubeObject.frontWall.opacity==undefined?'1':cubeObject.frontWall.opacity,
				transparent:true,
				map:cubeObject.frontWall.img==undefined? '':new THREE.ImageUtils.loadTexture(cubeObject.frontWall.img),
                side:THREE.DoubleSide,
                name:'aaa',
                //visible:false
			}
		));
	}else{
		cubeMaterialArray.push( new THREE.MeshBasicMaterial( { color: 0x8EC6C6} ) );//正面
	}
	
	//后面
	if(cubeObject.backWall!=undefined){
		cubeMaterialArray.push( new THREE.MeshBasicMaterial( 
			{ 	color:cubeObject.backWall.color==undefined?'':cubeObject.backWall.color ,
				opacity:cubeObject.backWall.opacity==undefined?'1':cubeObject.backWall.opacity,
				transparent:true,
				map:cubeObject.backWall.img==undefined? '':new THREE.ImageUtils.loadTexture(cubeObject.backWall.img),
                side:THREE.DoubleSide
			}
		) );
	}else{
		cubeMaterialArray.push( new THREE.MeshBasicMaterial( 
			{ 	color: 0x8EC6C6 ,
			} 
		) );//后面
	}
	
	//上边
	if(cubeObject.topWall!=undefined){
		cubeMaterialArray.push( new THREE.MeshBasicMaterial( 
			{ 	color:cubeObject.topWall.color==undefined?'':cubeObject.topWall.color ,
				opacity:cubeObject.topWall.opacity==undefined?'1':cubeObject.topWall.opacity,
				transparent:true,
				map:cubeObject.topWall.img==undefined? '':new THREE.ImageUtils.loadTexture(cubeObject.topWall.img),
                side:THREE.DoubleSide
			} 
		) );
	}else{
		cubeMaterialArray.push( new THREE.MeshBasicMaterial( { color: 0x8EC6C6 } ) );
	}
	
	//下边
	if(cubeObject.bottomWall!=undefined){
		cubeMaterialArray.push( new THREE.MeshBasicMaterial( 
			{ 	color:cubeObject.bottomWall.color==undefined?'':cubeObject.bottomWall.color ,
				opacity:cubeObject.bottomWall.opacity==undefined?'1':cubeObject.bottomWall.opacity,
				transparent:true,
				map:cubeObject.bottomWall.img==undefined? '':new THREE.ImageUtils.loadTexture(cubeObject.bottomWall.img),
                side:THREE.DoubleSide
			} 
		) );
	}else{
		cubeMaterialArray.push( new THREE.MeshBasicMaterial( { color: 0xEC6C6 } ) );
	}
	
	//左边
	if(cubeObject.leftWall!=undefined){
		cubeMaterialArray.push( new THREE.MeshBasicMaterial( 
			{	color:cubeObject.leftWall.color==undefined?'':cubeObject.leftWall.color ,
				opacity:cubeObject.leftWall.opacity==undefined?'1':cubeObject.leftWall.opacity,
				transparent:true,
				map:cubeObject.leftWall.img==undefined? '':new THREE.ImageUtils.loadTexture(cubeObject.leftWall.img),
                side:THREE.DoubleSide
			}
		) );
	}else{
		cubeMaterialArray.push( new THREE.MeshBasicMaterial( { color: 0x8EC6C6 } ) );
	}
	
	//右边
	if(cubeObject.rightWall!=undefined){
		cubeMaterialArray.push( new THREE.MeshBasicMaterial( 
			{ 	color:cubeObject.rightWall.color==undefined?'':cubeObject.rightWall.color ,
				opacity:cubeObject.rightWall.opacity==undefined?'1':cubeObject.rightWall.opacity,
				transparent:true,
				map:cubeObject.rightWall.img==undefined? '':new THREE.ImageUtils.loadTexture(cubeObject.rightWall.img),
                side:THREE.DoubleSide
			} ) );
	}else{
		cubeMaterialArray.push( new THREE.MeshBasicMaterial( { color: 0x8EC6C6 } ) );
	}
	
	var cubeMaterials = new THREE.MeshFaceMaterial( cubeMaterialArray );
	
	/*称为线性方式渲染
	var cubeMaterials = new THREE.MeshBasicMaterial({
	    wireframe : true
	});
	*/
	// Cube parameters: width (x), height (y), depth (z), 
	//        (optional) segments along x, segments along y, segments along z
	//代码中出现了THREE.CubeGeometry，THREE.CubeGeometry是什么东东，他是一个几何体
	var cubeGeometry = new THREE.CubeGeometry(cubeObject.x, cubeObject.y, cubeObject.z, 1, 1, 1 );
	cubeGeometry.id=cubeObject.id;
	cubeGeometry.name=cubeObject.name;
	cubeGeometry.y=cubeObject.y
	
	// using THREE.MeshFaceMaterial() in the constructor below
	//   causes the mesh to use the materials stored in the geometry
	cube = new THREE.Mesh( cubeGeometry, cubeMaterials );
	cube.position.set(cubeObject.positionX, cubeObject.positionY, cubeObject.positionZ);
	scene.add( cube );	
	
	//将正方形加入数组中，点击事件用
	if(targetList!=undefined){
		targetList.push(cube);
	}
	
}




function render() 
{	
	baseDataObject.renderer.render(baseDataObject.scene, baseDataObject.camera );
}



function update()
{
	// delta = change in time since last call (in seconds)
	var delta = clock.getDelta(); 

	// functionality provided by THREEx.KeyboardState.js
	if ( keyboard.pressed("1") )
		document.getElementById('message').innerHTML = ' Have a nice day! - 1';	
	if ( keyboard.pressed("2") )
		document.getElementById('message').innerHTML = ' Have a nice day! - 2 ';	
		
	controls.update();
	baseDataObject.stats.update();
}




function animate() 
{
    requestAnimationFrame( animate );
	render();		
	update();
}












function init(baseDataObject,targetList) 
{
	//场景（scene）、相机（camera）和渲染器（renderer）。有了这三样东西，才能将物体渲染到网页中去。
	baseDataObject.scene.add(baseDataObject.camera);
	// the camera defaults to position (0,0,0)
	// 	so pull it back (z = 400) and up (y = 100) and set the angle towards the scene origin
	//设置镜头位置第一种方式
	/*
	camera.position.x = 0;
	camera.position.y = 400;
	camera.position.z = 800;
	camera.up.x = 0;
	camera.up.y = 1;
	camera.up.z = 0;
	camera.lookAt({
	    x : 0,
	    y : 0,
	    z : 0
	});
	*/
	//设置镜头位置第二种方式
	
	baseDataObject.camera.position.set(0, 800, 500);
	baseDataObject.camera.lookAt(baseDataObject.scene.position);
	
	//////////////
	// RENDERER //
	//////////////
	
	// create and start the renderer; choose antialias setting.
	if ( Detector.webgl )
		baseDataObject.renderer = new THREE.WebGLRenderer( {antialias:true} );
	else
		baseDataObject.renderer = new THREE.CanvasRenderer(); 
	
	baseDataObject.renderer.setClearColor(0x123539, 1);
	baseDataObject.renderer.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
	
	// attach div element to variable to contain the renderer
	//container = document.getElementById( 'ThreeJS' );
	baseDataObject.container.appendChild( baseDataObject.renderer.domElement );
	
	////////////
	// EVENTS //
	////////////

	// automatically resize renderer
	THREEx.WindowResize(baseDataObject.renderer, baseDataObject.camera);
	// toggle full-screen on given key press
	THREEx.FullScreen.bindKey({ charCode : 'm'.charCodeAt(0) });
	
	//////////////
	// CONTROLS //
	//////////////

	// move mouse and: left   click to rotate, 
	//                 middle click to zoom, 
	//                 right  click to pan
	controls = new THREE.OrbitControls( baseDataObject.camera, baseDataObject.renderer.domElement );
	
	///////////
	// STATS //
	///////////
	
	// displays current and past frames per second attained by scene
	//stats = new Stats();
	baseDataObject.stats.domElement.style.position = 'absolute';
	baseDataObject.stats.domElement.style.bottom = '0px';
	baseDataObject.stats.domElement.style.zIndex = 100;
	baseDataObject.container.appendChild( baseDataObject.stats.domElement );
	
	///////////
	// LIGHT //
	///////////
	
	// create a light
	var light = new THREE.PointLight(0xffffff);
	light.position.set(0,250,0);
	baseDataObject.scene.add(light);
	var ambientLight = new THREE.AmbientLight(0x111111);
	baseDataObject.scene.add(ambientLight);
	
	//创建柜子
	/* 箱子底板放在外面了
	var cabinetBottom = {
		x:boxDepth/3,y:cabinetThickness,z:topBottomBroadLength,
		name:'柜子底板',
		positionX:-150,positionY:0,positionZ:0,
		id:'cabinetBottom',
		topWall:{color:0x777777},
		};
	*/
	createCubeGeometry(cabinetBottom,cube,baseDataObject.scene);
	
	var cabinetTop = {
		x:boxDepth/3,y:cabinetThickness,z:topBottomBroadLength,
		name:'柜子顶板',
		//positionY的计算有问题
		positionX:cabinetBottom.positionX,positionY:cabinetHeight,positionZ:cabinetBottom.positionZ,
		id:'cabinetTop',
		topWall:{color:0x777777},
		};
	createCubeGeometry(cabinetTop,cube,baseDataObject.scene);
	//出箱的背面
	var cabinetBack = {
		x:cabinetThickness,y:cabinetHeight,z:topBottomBroadLength,
		name:'柜子背面',
		positionX:cabinetBottom.positionX-boxDepth/6,positionY:cabinetHeight/2,positionZ:cabinetBottom.positionZ,
		id:'cabinetBack',
		frontWall:{color:0xFF7792,img:basePath+'/static/3D/images/a.jpg'},
		};
	createCubeGeometry(cabinetBack,cube,baseDataObject.scene);
	
	var cabinetRight = {
		x:boxDepth/3,y:cabinetHeight,z:cabinetThickness,
		name:'柜子右面',
		positionX:cabinetBottom.positionX,positionY:cabinetHeight/2,positionZ:cabinetBottom.positionZ-(topBottomBroadLength/2),
		id:'cabinetRight',
		frontWall:{color:0xDDDDDD},
		};
	createCubeGeometry(cabinetRight,cube,baseDataObject.scene);
	
	var cabinetLeft = {
		x:boxDepth/3,y:cabinetHeight,z:cabinetThickness,
		name:'柜子左面',
		positionX:cabinetBottom.positionX,positionY:cabinetHeight/2,positionZ:cabinetBottom.positionZ+(topBottomBroadLength/2),
		id:'cabinetLeft',
		leftWall:{color:0xDDDDDD,opacity:0.5},
		};
	createCubeGeometry(cabinetLeft,cube,baseDataObject.scene);
	//创建柜子结束
	
	//创建箱子
	createBox(cube,baseDataObject.scene,targetList);
	
	
	
	/**正方形结束***/
	
	// create a set of coordinate axes to help orient user
	//    specify length in pixels in each direction,中间的十字线
	/*
	var axes = new THREE.AxisHelper(100);
	scene.add( axes );
	*/
	//十字线结束
	
	///////////
	// FLOOR //
	///////////
	
	// note: 4x4 checkboard pattern scaled so that each square is 25 by 25 pixels.
	//处理背景
	var floorTexture = new THREE.ImageUtils.loadTexture( basePath+'/static/3D/images/checkerboard.jpg' );
	floorTexture.wrapS = floorTexture.wrapT = THREE.RepeatWrapping; 
	floorTexture.repeat.set(30, 30);
	// DoubleSide: render texture on both sides of mesh
	var floorMaterial = new THREE.MeshBasicMaterial( { map: floorTexture, side: THREE.DoubleSide } );
	var floorGeometry = new THREE.PlaneGeometry(1000, 1500, 1, 1);
	var floor = new THREE.Mesh(floorGeometry, floorMaterial);
	floor.position.y = -0.5;
	floor.rotation.x = Math.PI / 2;
	baseDataObject.scene.add(floor);
	
	/////////
	// SKY //
	/////////
	
	// recommend either a skybox or fog effect (can't use both at the same time) 
	// without one of these, the scene's background color is determined by webpage background

	// make sure the camera's "far" value is large enough so that it will render the skyBox!
	var skyBoxGeometry = new THREE.CubeGeometry( 10000, 10000, 10000 );
	// BackSide: render faces from inside of the cube, instead of from outside (default).
	var skyBoxMaterial = new THREE.MeshBasicMaterial( { color: 0x9999ff, side: THREE.BackSide } );
	var skyBox = new THREE.Mesh( skyBoxGeometry, skyBoxMaterial );
	// scene.add(skyBox);
	
	// fog must be added to scene before first render
	baseDataObject.scene.fog = new THREE.FogExp2( 0x9999ff, 0.00025 );
	
	//点击事件用
	// initialize object to perform world/screen calculations
	projector = new THREE.Projector();
	
	//点击事件用
	// when the mouse moves, call the given function
	baseDataObject.renderer.domElement.addEventListener( 'mousedown', onDocumentMouseDown, false );
}