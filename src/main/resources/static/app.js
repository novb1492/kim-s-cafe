
let squaretable;
let value = 0;
let square=100;
let anoters;
let circle;
let circlesize=200;
function setup() {
  createCanvas(windowWidth, windowHeight);
}
function setup() {
    var framerate=60;
    frameRate(framerate);
    let canvas=createCanvas(displayWidth,displayHeight);
    canvas.parent('canvas');
    squaretable=new Square();
    anoters=new Anothers();
    circle=new Circletable();

  }


function draw() 
{  
  background('#fae');
  anoters.draw();
  fill(value);
  squaretable.draw(10,200,200,50);
  squaretable.draw(10,400,200,50);
  squaretable.draw(10,600,200,50);
  squaretable.draw(300,windowHeight/2+25,square,square);
  squaretable.draw(500,windowHeight/2+25,square,square);
  squaretable.draw(700,windowHeight/2+25,square,square);
  squaretable.draw(300,windowHeight/2-25,square,-square);
  squaretable.draw(500,windowHeight/2-25,square,-square);
  squaretable.draw(700,windowHeight/2-25,square,-square);
  circle.draw(400,windowHeight-circlesize+50,circlesize);
  squaretable.draw(700,windowHeight/2-25,square,-square);

}