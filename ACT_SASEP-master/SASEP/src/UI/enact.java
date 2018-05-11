package UI;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Label;


public class enact {

	protected Shell shell;
	private Text text;
	//private Button btnNewButton_2;
	//private Button btnNewButton_1;
	Button btnNewButton_2;
	private ScrolledComposite composite0;
	private Composite composite;
	private Composite composite_1;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private int MAX = 10;
	private Group[] phy = new Group[MAX];
	private Text text_4;
	private Text text_5;
	private Button button;
	/////
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			enact window = new enact();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(445, 300);
		shell.setText("SWT Application");
		shell.setLayout(null);
		
		Group group = new Group(shell, SWT.NONE);
		group.setBounds(10, 10, 120, 230);
		group.setText("???");
		group.setLayout(null);
		
		text = new Text(group, SWT.BORDER);
		text.setBounds(10, 20, 31, 23);
	//	Button btnNewButton_2 = new Button(composite, SWT.NONE);

		Button btnNewButton = new Button(group, SWT.NONE);
		btnNewButton.setBounds(10, 80, 80, 27);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int x = Integer.parseInt(text.getText());
				for(int i=0;i<x;i++)
				{
					phy[i] = new Group(composite, SWT.NONE);
					if(i<5)
						phy[i].setBounds(10+i*100, 10, 97, 139);
					else
						phy[i].setBounds(10+(i-5)*100, 10+140, 97, 139);
					text_1 = new Text(phy[i], SWT.BORDER);
					text_1.setBounds(10, 20, 73, 23);
					
					Button btnNewButton_1 = new Button(phy[i], SWT.NONE);
					btnNewButton_1.setBounds(0, 39, 80, 61);
					btnNewButton_1.setText("New Button");
					
					text_2 = new Text(phy[i], SWT.BORDER);
					text_2.setBounds(24, 106, 47, 9);
					
					text_3 = new Text(phy[i], SWT.BORDER);
					text_3.setBounds(24, 121, 47, 8);
					
					phy[i].setText(text_4.getText());
					phy[i].moveAbove(null);
				}
/*				composite0.redraw();composite0.layout();
				composite.redraw();composite.layout();
				shell.redraw();shell.layout();*/
			}
		});
		btnNewButton.setText("New Button");
		
		composite0 = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		composite0.setBounds(166, 20, 900, 760);
		composite0.setContent(composite);
		

		composite = new Composite(composite0, SWT.NONE);

		composite.setSize(5000, 5000); 
		composite0.setContent(composite);

		Button btnCheckButton = new Button(group, SWT.CHECK);
		btnCheckButton.setBounds(10, 49, 98, 20);
		btnCheckButton.setText("Check Button");
		
		text_4 = new Text(group, SWT.BORDER);
		text_4.setBounds(47, 20, 63, 23);
		
		text_5 = new Text(group, SWT.BORDER);
		text_5.setBounds(10, 132, 73, 23);
		
		button = new Button(group, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				final String textString;
				Shell shell = new Shell();
			    FileDialog dialog = new FileDialog(shell,SWT.OPEN);
			    dialog.setFilterExtensions(new String[] { "*.xml", "*.*" });
			    String name = dialog.open();
			    if ((name == null) || (name.length() == 0))
			     return;
			    try {
			     File file = new File(name);
			     FileInputStream stream = new FileInputStream(file.getPath());
			     text_5.setText(file.getPath());
			     try {
			      Reader in = new BufferedReader(new InputStreamReader(
			        stream));
			      char[] readBuffer = new char[2048];
			      StringBuffer buffer = new StringBuffer((int) file
			        .length());
			      int n;
			      while ((n = in.read(readBuffer)) > 0) {
			       buffer.append(readBuffer, 0, n);
			      }
			      textString = buffer.toString();
			      stream.close();
			     } catch (IOException e1) {
			      MessageBox box = new MessageBox(shell, SWT.ICON_ERROR);
			      box.setMessage("Cannot open!\n" + name);
			      box.open();
			      return;
			     }
			    } catch (FileNotFoundException e2) {
			     MessageBox box = new MessageBox(shell, SWT.ICON_ERROR);
			     box.setMessage("Not Found!\n" + name);
			     box.open();
			     return;
			    }
			    //text.setText(textString);
			}
		});
		button.setBounds(3, 176, 80, 27);
		button.setText("!!");


		//composite_1 = new Composite(composite0, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		//composite0.setContent(composite_1);
		//composite0.setMinSize(composite_1.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		
		
		
	}
}
