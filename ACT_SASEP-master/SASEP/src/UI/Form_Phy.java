package UI;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

import java.io.*;

public class Form_Phy extends Dialog {

	protected Object result;
	protected Shell shlFormphy;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public Form_Phy(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlFormphy.open();
		shlFormphy.layout();
		Display display = getParent().getDisplay();
		while (!shlFormphy.isDisposed()) {
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
		shlFormphy = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shlFormphy.setSize(516, 336);
		shlFormphy.setText("Env Res");
		
		Group grpPhysicResource = new Group(shlFormphy, SWT.NONE);
		grpPhysicResource.setText("Resource");
		grpPhysicResource.setBounds(10, 10, 490, 287);
		
		Label lblResName = new Label(grpPhysicResource, SWT.NONE);
		lblResName.setBounds(10, 30, 71, 17);
		lblResName.setText("Res Name:");
		
		text = new Text(grpPhysicResource, SWT.BORDER);
		text.setBounds(87, 30, 100, 23);
		
		Group grpModel = new Group(grpPhysicResource, SWT.NONE);
		grpModel.setText("Model");
		grpModel.setBounds(10, 70, 126, 141);
		
		Label lblType = new Label(grpModel, SWT.NONE);
		lblType.setBounds(10, 23, 61, 17);
		lblType.setText("Type:");
		
		Combo combo = new Combo(grpModel, SWT.NONE);
		combo.setBounds(20, 46, 88, 25);
		combo.setItems(new String[] {"number", "dict", "str", "list"});
		combo.select(0);
		
		Button btnInitvalue = new Button(grpModel, SWT.CHECK);
		btnInitvalue.setBounds(10, 77, 98, 17);
		btnInitvalue.setText("Init_Value:");
		
		text_1 = new Text(grpModel, SWT.BORDER);
		text_1.setBounds(20, 100, 88, 23);
		
		Group grpUpdate = new Group(grpPhysicResource, SWT.NONE);
		grpUpdate.setText("Update");
		grpUpdate.setBounds(156, 71, 324, 170);
		
		Label lblDelay = new Label(grpUpdate, SWT.NONE);
		lblDelay.setBounds(10, 24, 46, 17);
		lblDelay.setText("Delay:");
		
		text_2 = new Text(grpUpdate, SWT.BORDER);
		text_2.setBounds(64, 23, 73, 23);
		
		Label lblNext = new Label(grpUpdate, SWT.NONE);
		lblNext.setBounds(171, 24, 46, 17);
		lblNext.setText("Next:");
		
		text_3 = new Text(grpUpdate, SWT.BORDER);
		text_3.setBounds(223, 23, 73, 23);
		
		Label lblRule = new Label(grpUpdate, SWT.NONE);
		lblRule.setBounds(10, 89, 46, 17);
		lblRule.setText("Rule:");
		
		text_4 = new Text(grpUpdate, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL);
		text_4.setBounds(64, 81, 232, 79);
		
		Label lblDefaultfunc = new Label(grpUpdate, SWT.NONE);
		lblDefaultfunc.setBounds(10, 55, 83, 18);
		lblDefaultfunc.setText("Default_Func:");
		
		Combo combo_1 = new Combo(grpUpdate, SWT.NONE);
		combo_1.setItems(new String[] {"METHOD_MATH_ADD", "METHOD_MATH_DIVISION", "METHOD_MATH_MINUS", "METHOD_MATH_MULTIPLY", "METHOD_MATH_MOD", "METHOD_MATH_LINEAR", "METHOD_MATH_SIN", "METHOD_MATH_LOG", "METHOD_MATH_SUM", "METHOD_MATH_MEAN", "METHOD_PROBABILITY_MARKOV_CHAIN", "METHOD_PROBABILITY_SIMPLE_RAND", "METHOD_PROBABILITY_NORMAL_VARIATE_RAND", "METHOD_OTHERS_COMBINE", "METHOD_OTHERS_DATA_LIST"});
		combo_1.setBounds(99, 52, 197, 25);
		combo_1.select(0);

		final Combo combo_2 = new Combo(grpPhysicResource, SWT.NONE);
		combo_2.setItems(new String[] {"TextBar", "Image"});
		combo_2.setBounds(254, 30, 78, 25);
		combo_2.select(1);

		Button btnCreate = new Button(grpPhysicResource, SWT.NONE);
		btnCreate.setBounds(35, 217, 80, 27);
		btnCreate.setText("Create");

		Label lblClass = new Label(grpPhysicResource, SWT.NONE);
		lblClass.setBounds(344, 33, 33, 17);
		lblClass.setText("Class:");

		final Combo combo_3 = new Combo(grpPhysicResource, SWT.NONE);
		combo_3.setItems(new String[] {"Physical Resource", "Device Resource", "Properties"});
		combo_3.setBounds(381, 30, 99, 25);
		combo_3.select(2);


		Label lblDisplay = new Label(grpPhysicResource, SWT.NONE);
		lblDisplay.setBounds(199, 33, 49, 17);
		lblDisplay.setText("Display:");
		
		text_5 = new Text(grpPhysicResource, SWT.BORDER);
		text_5.setBounds(65, 254, 316, 23);
		
		Button btnBrower = new Button(grpPhysicResource, SWT.NONE);
		btnBrower.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				final String textString;
				Shell shell = new Shell();
			    FileDialog dialog = new FileDialog(shell,SWT.OPEN);
			    dialog.setFilterExtensions(new String[] { "*.jpg", "*.*" });
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
			      box.setMessage("Read Error\n" + name);
			      box.open();
			      return;
			     }
			    } catch (FileNotFoundException e2) {
			     MessageBox box = new MessageBox(shell, SWT.ICON_ERROR);
			     box.setMessage("Not Found\n" + name);
			     box.open();
			     return;
			    }
			    //text.setText(textString);
			}
		});
		btnBrower.setBounds(400, 252, 80, 27);
		btnBrower.setText("Brower");
		
		Label lblImage = new Label(grpPhysicResource, SWT.NONE);
		lblImage.setBounds(10, 256, 49, 17);
		lblImage.setText("Image:");

		btnCreate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(combo_3.getText().equals("Device Resource"))
				{
					shell.lblAddDevresHere.setVisible(false);
					Group group = new Group(shell.composite_dev_con, SWT.NONE);
					group.setBounds(10+shell.col_dev*170, 10+shell.row_dev*190, 150, 179);
					group.setText(text.getText());
					group.setLayout(null);
					if(shell.col_dev<3)
						shell.col_dev++;
					else
					{
						shell.col_dev=0;
						shell.row_dev++;
					}
					final Button button = new Button(group, SWT.BORDER);
					button.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							Shell shell = new Shell();
							LoadFile lf = new LoadFile(shell,SWT.ALPHA);
							lf.open();
						}
					});
					if(text_5.getText().equals(""))
						button.setImage(SWTResourceManager.getImage("E:\\MyEclipse\\pfUI\\imagePF\\kaichl.jpg"));
					else
						button.setImage(SWTResourceManager.getImage(text_5.getText()));
					button.setBounds(10, 45, 100, 100);

					Label lblState = new Label(group, SWT.NONE);
					lblState.setBounds(10, 151, 37, 17);
					lblState.setText("State:");

					Text txt = new Text(group, SWT.BORDER);
					if(text_1.getText().equals(""))
						txt.setText("On");
					else
						txt.setText(text_1.getText());
					txt.setBounds(50, 151, 56, 18);
				}
				else if(combo_3.getText().equals("Properties"))
				{
					shell.lblAddPropertiesHere.setVisible(false);

					Label lbl = new Label(shell.composite_pro_con, SWT.NONE);
					lbl.setAlignment(SWT.RIGHT);
					lbl.setBounds(8, 10+shell.row_pro*35,100, 18);
					lbl.setText(text.getText()+":");

					Text txt = new Text(shell.composite_pro_con, SWT.BORDER);
					if(text_1.getText().equals(""))
						txt.setText("1");
					else
						txt.setText(text_1.getText());
					txt.setBounds(136, 10+shell.row_pro*35, 73, 23);

					shell.row_pro++;
				}
				else
				{
					shell.lblAddPhyresHere.setVisible(false);

					if(combo_2.getText().equals("TextBar"))
					{
						Label lbl = new Label(shell.composite_phy_con, SWT.NONE);
						lbl.setAlignment(SWT.RIGHT);
						lbl.setBounds(8, 10 + shell.row_phy * 35, 50, 18);
						lbl.setText(text.getText() + ":");

						Text txt = new Text(shell.composite_phy_con, SWT.BORDER);
						if (text_1.getText().equals(""))
							txt.setText("1");
						else
							txt.setText(text_1.getText());
						txt.setBounds(86, 10 + shell.row_phy * 35, 73, 23);

						shell.row_phy++;
					}
					else
					{
						Label lbl = new Label(shell.composite_phy_con, SWT.NONE);
						lbl.setAlignment(SWT.RIGHT);
						lbl.setBounds(180, 10 + shell.col_phy * 120, 50, 18);
						lbl.setText(text.getText() + ":");

						final Button button = new Button(shell.composite_phy_con, SWT.BORDER);
						button.addSelectionListener(new SelectionAdapter() {
							@Override
							public void widgetSelected(SelectionEvent e) {
								Shell shell = new Shell();
								LoadFile lf = new LoadFile(shell,SWT.ALPHA);
								lf.open();
							}
						});
						if(text_5.getText().equals(""))
							button.setImage(SWTResourceManager.getImage("E:\\MyEclipse\\pfUI\\imagePF\\kaichl.jpg"));
						else
							button.setImage(SWTResourceManager.getImage(text_5.getText()));
						button.setBounds(258, 10+shell.col_phy * 120, 100, 100);

						shell.col_phy++;
					}
				}
			}
		});
	}
}
