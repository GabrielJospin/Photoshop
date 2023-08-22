package photoshop.filters;

import photoshop.Image;

public abstract class Filter {
    
    Image imageIn;
    Image imageOut;

    Filter(Image imageIn, Image imageOut){
        this.imageIn = imageIn;
        this.imageOut = imageOut;
    }

    public abstract void apply();
    
}
