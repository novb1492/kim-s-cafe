class Anothers{
    constructor(){

    }
    draw(){
        line(0, windowHeight/2, windowWidth, windowHeight/2);
        line(windowWidth/2, 0, windowWidth/2, windowHeight);
        push();
        line(0, windowHeight/10, windowWidth/4, windowHeight/10);
        line(windowWidth/4, windowHeight/10, windowWidth/4, 0);
        pop();
    }
}