// Owen Wilhere
// 02/13/2023
// CSE 123
// TA: Jay Dharmadhikari
// This class is Mondrian. It randomly generates mondrian style art.
import java.util.*;
import java.awt.*;

public class Mondrian {

    // This method takes a 2D array of Color named pixels and generates a 
    // mondrian image. It splits the array into smaller sections at random 
    // and colors each section at random.
    // No returns.
    public void paintBasicMondrian(Color[][] pixels){
        Random ran = new Random();
        paintBasicMondrian(0, 0, pixels[0].length, pixels.length, ran, pixels);
    }

    // This method takes a 2D array of Color named pixels and generates a 
    // mondrian image. It splits the array into smaller sections at random 
    // and colors each section based on the position of the section. Colors
    // are randomly chosen but sections in certain quadrants of the canvas
    // are more likely to be a certain color depending on the quadrant.
    // No returns.
    public void paintComplexMondrian(Color[][] pixels){
        Random ran = new Random();
        paintComplexMondrian(0, 0, pixels[0].length, pixels.length, ran, pixels);
    }

    // This method takes a 2D array of Color named pixels and generates a 
    // mondrian image. It splits the array into smaller sections at random 
    // and colors each section at random.
    // Paramaters:  -int indexH1: the starting vertival bound of the section 
    //              -int indexW1: the starting horizantal bound of the sction
    //              -int indexH2: the ending vertical bound of the section
    //              -int indexW2: the ending horizantal bound of the section
    //              -Random ran: a Random object
    //              -Color[][] pixels: 2D array of Color objects
    // No returns.
    private void paintBasicMondrian(int indexH1, int indexW1, int indexH2, 
                                int indexW2, Random ran, Color[][] pixels){
        if(indexH2 - indexH1 > pixels[0].length / 4 
           && indexW2 - indexW1 > pixels.length / 4){
            int ranH = ran.nextInt(indexH1, indexH2);
            int ranW = ran.nextInt(indexW1, indexW2);
            //top left
            paintBasicMondrian(indexH1, indexW1, ranH, ranW, ran, pixels);
            //top right
            paintBasicMondrian(indexH1, ranW, ranH, indexW2, ran, pixels);
            //bottom left
            paintBasicMondrian(ranH, indexW1, indexH2, ranW, ran, pixels);
            //bottom right
            paintBasicMondrian(ranH, ranW, indexH2, indexW2, ran, pixels);
        } else if(indexW2 - indexW1 > pixels.length / 4){
            int ranW = ran.nextInt(indexW1, indexW2);
            //left
            paintBasicMondrian(indexH1, indexW1, indexH2, ranW, ran, pixels);
            //right
            paintBasicMondrian(indexH1, ranW, indexH2, indexW2, ran, pixels);
        } else if(indexH2 - indexH1 > pixels[0].length / 4){
            int ranH = ran.nextInt(indexH1, indexH2);
            //up
            paintBasicMondrian(indexH1, indexW1, ranH, indexW2, ran, pixels);
            //below
            paintBasicMondrian(ranH, indexW1, indexH2, indexW2, ran, pixels);
        } else {
            colorRegion(indexH1, indexW1, indexH2, indexW2, ran, pixels, true);
        }

    }

    // This method takes a 2D array of Color named pixels and generates a 
    // mondrian image. It splits the array into smaller sections at random 
    // and colors each section based on the position of the section. Colors
    // are randomly chosen but sections in certain quadrants of the canvas
    // are more likely to be a certain color depending on the quadrant.
    // Paramaters:  -int indexH1: the starting vertival bound of the section 
    //              -int indexW1: the starting horizantal bound of the sction
    //              -int indexH2: the ending vertical bound of the section
    //              -int indexW2: the ending horizantal bound of the section
    //              -Random ran: a Random object
    //              -Color[][] pixels: 2D array of Color objects
    // No returns.
    private void paintComplexMondrian(int indexH1, int indexW1, int indexH2, 
                                    int indexW2, Random ran, Color[][] pixels){
        if(indexH2 - indexH1 > pixels[0].length / 4 
           && indexW2 - indexW1 > pixels.length / 4){
            int ranH = ran.nextInt(indexH1, indexH2);
            int ranW = ran.nextInt(indexW1, indexW2);
            //top left
            paintComplexMondrian(indexH1, indexW1, ranH, ranW, ran, pixels);
            //top right
            paintComplexMondrian(indexH1, ranW, ranH, indexW2, ran, pixels);
            //bottom left
            paintComplexMondrian(ranH, indexW1, indexH2, ranW, ran, pixels);
            //bottom right
            paintComplexMondrian(ranH, ranW, indexH2, indexW2, ran, pixels);
        } else if(indexW2 - indexW1 > pixels.length / 4){
            int ranW = ran.nextInt(indexW1, indexW2);
            //left
            paintComplexMondrian(indexH1, indexW1, indexH2, ranW, ran, pixels);
            //right
            paintComplexMondrian(indexH1, ranW, indexH2, indexW2, ran, pixels);
        } else if(indexH2 - indexH1 > pixels[0].length / 4){
            int ranH = ran.nextInt(indexH1, indexH2);
            //up
            paintComplexMondrian(indexH1, indexW1, ranH, indexW2, ran, pixels);
            //below
            paintComplexMondrian(ranH, indexW1, indexH2, indexW2, ran, pixels);
        } else {
            colorRegion(indexH1, indexW1, indexH2, indexW2, ran, pixels, false);
        }          
    }

    // This method colors a region of the canvas randomly. It equally distributes
    // the probability of the colors if boolean basic = true, and distrubutes the 
    // probability of the colors based off of location / which quadrant the section
    // is located in if boolean basic = false;
    // Paramaters:  -int indexH1: the starting vertival bound of the section 
    //              -int indexW1: the starting horizantal bound of the sction
    //              -int indexH2: the ending vertical bound of the section
    //              -int indexW2: the ending horizantal bound of the section
    //              -Random ran: a Random object
    //              -Color[][] pixels: 2D array of Color objects
    //              -boolean basic: whether or not the method performs a basic or
    //                              complex color assignment
    // No returns.
    private void colorRegion(int indexH1, int indexW1, int indexH2, int indexW2,
                            Random ran, Color[][] pixels, boolean basic){
        Color color = Color.BLACK;
        if(basic){
            color = getBasicColor(ran);
        } else{
            color = getComplexColor(indexH1, indexW1, indexH2, indexW2,
                                    ran, pixels);
        }
        for(int i = indexW1 + 1; i < indexW2; i++){
            for(int j = indexH1 + 1; j < indexH2; j++){
                pixels[i][j] = color;
            }
        }
    }

    // This method returns a random color out of four(white, cyan, yellow, or red)
    // Parameter: Random ran - a Random object
    // Returns a random Color.
    private Color getBasicColor(Random ran) {
        int num = ran.nextInt(4);
        if(num == 0){
            return Color.WHITE;
        } else if(num == 1){
            return Color.CYAN;
        } else if(num == 2){
            return Color.YELLOW;
        }
        return Color.RED;
    }

    // This method returns a random color(white, cyan, yellow, or red) based off
    // the location of the section. Sections in certain quadrants of the canvas
    // are more likely to be a certain color depending on the quadrant.
    // Paramaters:  -int indexH1: the starting vertival bound of the section 
    //              -int indexW1: the starting horizantal bound of the sction
    //              -int indexH2: the ending vertical bound of the section
    //              -int indexW2: the ending horizantal bound of the section
    //              -Random ran: a Random object
    //              -Color[][] pixels: 2D array of Color objects
    // Returns a weighted random Color.
    private Color getComplexColor(int indexH1, int indexW1, int indexH2, 
                            int indexW2, Random ran, Color[][] pixels){
    int ranNum = ran.nextInt(9);                                
    Color color = Color.BLACK;
    //top left -- WHITE
    if(indexW2 <= pixels.length / 2 && indexH2 <= pixels[0].length / 2){
        if(ranNum <= 5){
            color = Color.WHITE;
        } else if(ranNum == 6){
            color = Color.CYAN;
        } else if(ranNum == 7){
            color = Color.YELLOW;
        } else if(ranNum == 8){
            color = Color.RED;
        }
    } 
    //bottom left -- CYAN
    else if(indexW1 >= pixels.length / 2 && indexH2 <= pixels[0].length / 2){
        if(ranNum <= 5){
            color = Color.CYAN;
        } else if(ranNum == 6){
            color = Color.WHITE;
        } else if(ranNum == 7){
            color = Color.YELLOW;
        } else if(ranNum == 8){
            color = Color.RED;
        }
    }
    //top right -- YELLOW
    else if(indexW2 <= pixels.length / 2 && indexH1 >= pixels[0].length / 2){
        if(ranNum <= 5){
            color = Color.YELLOW;            
        } else if(ranNum == 6){
            color = Color.WHITE;
        } else if(ranNum == 7){
            color = Color.CYAN;
        } else if(ranNum == 8){
            color = Color.RED;
        }
    }
    //bottom right -- RED
    else if(indexW1 >= pixels.length / 2 && indexH1 >= pixels[0].length / 2){
        if(ranNum <= 5){
            color = Color.RED;            
        } else if(ranNum == 6){
            color = Color.WHITE;
        } else if(ranNum == 7){
            color = Color.CYAN;
        } else if(ranNum == 8){
            color = Color.YELLOW;
        }
    }
    //somewhere in between
    else {
        color = getBasicColor(ran);
    }

    return color;
    }
}
