package plotbot;

/**
 * Interface for shape object that can be plotted.
 * 
 */
public interface Plottable {
	public void plot(PlotbotControl pc); //method to plot the shape object
	public void isSelectedToDraw(); //changes the status of the shape of type plottable to "To draw"
	public String getStatus(); //returns the status of the shape whether it is to be drawn or not
}