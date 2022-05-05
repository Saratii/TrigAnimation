

import java.awt.Color;
import java.awt.*;
import java.awt.geom.*;
public class Arc {
    private float centerX;
    private float centerY;
    private Color color;
    private float outVel;
    private float outAngle;
    private float radius;
    private float distance;
    private float arcLength;
    public Arc(float centerX, float centerY, Color color, float outVel, float outAngle, float radius, float distance, float arcLength){
        this.centerX = centerX;
        this.centerY = centerY;
        this.color = color;
        this.outVel = outVel;
        this.outAngle = outAngle;
        this.radius = radius;
        this.distance = distance;
        this.arcLength = arcLength; 
    }
    public void moob(float i){
        if(i<100){
            return;
        }
        if(distance<100){
            distance+=outVel;
        } else {
            outAngle += 0.0005;
            outAngle += i/25000;
        }
    }
    public void draw(Graphics2D g){
        g.setColor(color);
        Arc2D arc1 = new Arc2D.Double(centerX-radius+distance*Math.cos(outAngle), centerY-radius-distance*Math.sin(outAngle), 2*radius, 2*radius, (outAngle-arcLength/2)*180/Math.PI, arcLength*180/Math.PI, Arc2D.PIE);
        g.fill(arc1);
    }
}
