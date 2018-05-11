package UI;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.swtchart.Chart;
import org.swtchart.IAxis;
import org.swtchart.ILineSeries;
import org.swtchart.ISeries;
import org.swtchart.ISeries.SeriesType;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.swtchart.Chart;
import org.swtchart.ILineSeries;
import org.swtchart.ISeries.SeriesType;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Combo;
import java.util.ArrayList;
import java.util.List;

public class GoalChart extends Dialog {

	protected Object result;
	protected Shell shell;
	private static final int MARGIN = 5;

    public static double[] ySeries = { 0.0, 0.38, 0.71, 0.92, 1.0, 0.92,
            0.71, 0.38, 0.0, -0.38, -0.71, -0.92, -1.0, -0.92, -0.71, -0.38};

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public GoalChart(Shell parent, int style) {
		super(parent, style);
		setText("Goal Satisfaction Degree");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MIN | SWT.MAX | SWT.APPLICATION_MODAL);
		shell.setSize(503, 330);
		shell.setText(getText());
        shell.setLayout(new FillLayout(SWT.HORIZONTAL));
        
     // create a chart
        Chart chart = new Chart(shell, SWT.NONE);

        // get Y axis
        final IAxis yAxis = chart.getAxisSet().getYAxis(0);
		chart.getTitle().setText(" ");
		chart.getAxisSet().getXAxis(0).getTitle().setText("Clock");
		chart.getAxisSet().getYAxis(0).getTitle().setText("Satisfaction");
        // create line series
        ILineSeries series = (ILineSeries) chart.getSeriesSet().createSeries(
                SeriesType.LINE, "line series");

        series.setYSeries(ySeries);

        // adjust the axis range
        chart.getAxisSet().adjustRange();

	}
}


