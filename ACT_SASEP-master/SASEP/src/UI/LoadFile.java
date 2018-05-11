package UI;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import java.io.*;

public class LoadFile extends Dialog {

	protected Object result;
	protected Shell shlLoadConfigFile;
	private Text text;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public LoadFile(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlLoadConfigFile.open();
		shlLoadConfigFile.layout();
		Display display = getParent().getDisplay();
		while (!shlLoadConfigFile.isDisposed()) {
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
		shlLoadConfigFile = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL);
		shlLoadConfigFile.setSize(371, 190);
		shlLoadConfigFile.setText("Set Image");
		
		Label lblSelectAnImage = new Label(shlLoadConfigFile, SWT.NONE);
		lblSelectAnImage.setBounds(20, 24, 105, 21);
		lblSelectAnImage.setText("Select an image");
		
		text = new Text(shlLoadConfigFile, SWT.BORDER);
		text.setBounds(20, 60, 249, 21);
		
		Button btnBrowse = new Button(shlLoadConfigFile, SWT.NONE);
		btnBrowse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
					final String textString;
					Shell shell = new Shell();
				    FileDialog dialog = new FileDialog(shell,SWT.OPEN);
				    dialog.setFilterExtensions(new String[] { "*.java", "*.*" });
				    String name = dialog.open();
				    if ((name == null) || (name.length() == 0))
				     return;
				    try {
				     File file = new File(name);
				     FileInputStream stream = new FileInputStream(file.getPath());
				     text.setText(file.getPath());
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
				      box.setMessage("读文件出错：\n" + name);
				      box.open();
				      return;
				     }
				    } catch (FileNotFoundException e2) {
				     MessageBox box = new MessageBox(shell, SWT.ICON_ERROR);
				     box.setMessage("文件未找到：\n" + name);
				     box.open();
				     return;
				    }
				    //text.setText(textString);
			}
		});
		btnBrowse.setBounds(275, 58, 80, 25);
		btnBrowse.setText("Browse");
		
		Button btnLoad = new Button(shlLoadConfigFile, SWT.NONE);
		btnLoad.setBounds(76, 110, 80, 25);
		btnLoad.setText("Submit");
		
		Button btnCancel = new Button(shlLoadConfigFile, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlLoadConfigFile.close();
			}
		});
		btnCancel.setBounds(241, 110, 80, 25);
		btnCancel.setText("Cancel");

	}
}

