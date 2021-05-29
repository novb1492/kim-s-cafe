
let squaretable;

function setup() {
    var framerate=60;
    frameRate(framerate);
    let canvas=createCanvas(displayWidth,windowHeight);
    canvas.parent('canvas');
    squaretable=new Square();
  }

function draw() 
{  
  background('gray');
  squaretable.draw();
  console.log(mouseX);
}