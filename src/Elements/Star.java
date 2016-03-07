package Elements;

import java.awt.geom.Path2D;

/**
 * Created by Dexter on 3/7/2016.
 */
public class Star extends Path2D.Double {
    public Star(int pos_x, int pos_y) {
        super(WIND_EVEN_ODD);
        this.moveTo(pos_x, pos_y);
        this.lineTo(pos_x-1.5, pos_y+5);
        this.lineTo(pos_x-7, pos_y+5);
        this.lineTo(pos_x-2.5, pos_y+8);
        this.lineTo(pos_x-4.2, pos_y+13);
        this.lineTo(pos_x+0, pos_y+10);
        this.lineTo(pos_x+4.2, pos_y+13);
        this.lineTo(pos_x+2.5, pos_y+8);
        this.lineTo(pos_x+7, pos_y+5);
        this.lineTo(pos_x+1.5, pos_y+5);
        this.lineTo(pos_x, pos_y);
    }
}