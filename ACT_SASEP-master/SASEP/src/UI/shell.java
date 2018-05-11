package UI;

import com.skyspace.client.ResPoolClient;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Composite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Timer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.custom.TableEditor;
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
import org.swtchart.Chart;
import org.swtchart.ILineSeries;
import org.swtchart.ISeries;


public class shell {
	//jichen
	public int flag = 1;
	public static int testInt = 0;
	public static Display display;
    public static Timer timer;
	public static TimerAction timerAction;
	public static int clock_step = 15000;
	public static ResPoolClient client = new ResPoolClient();
	public static int clock = 0;
	public static char[] flag_c = new char[5];
	public static int flag_sample;
	private int flag_ChuangLian=0;
	private int flag_Light=0;
	private int flag_pc=0;
	private int flag_air=0;
	private int flag_man=0;
	private int flag_tem=0;
	private int flag_Heater=0;
	private int flag_TV=0;
	private int flag_blind=0;
	//

	//enact
	public static int col_dev = 0;
	public static int row_dev = 0;
	public static int col_phy = 0;
	public static int row_phy = 0;
	public static int col_pro = 0;
	public static int row_pro = 0;
	//

	private ScrolledComposite composite_phy;
	public static Composite composite_phy_con;
	private ScrolledComposite composite_pro;
	public static Composite composite_pro_con;
	private ScrolledComposite composite_dev;
	public static Composite composite_dev_con;
	public static Label lblAddPhyresHere;
	public static Label lblAddDevresHere;
	public static Label lblAddPropertiesHere;
	
	public static Chart chart;
	public static final int MARGIN = 5;
	public static double[] ySeries = {0};
	
	public static Shell shell;
	public static Text text;
	public static Text text_1;
	public static Text text_2;
	public static Table table;
	public static Combo combo_2;
	public static Text text_time;
	public static Text text_season;
	public static Text text_weather;
	public static Text text_bri;
	public static Text text_hum;
	public static Text text_vol;
	public static Text text_tem;
	public static Text text_pow;
	public static Text text_air;
	public static Text txtOn;
	public static Text txtOff;
	public static Text text_3;
	public static Text txtOff_1;
	public static Text txtOff_2;
	public static Text text_5;
	public static Text text_6;
	public static Text txtOff_3;
	public static Text txtOff_4;
	public static Text text_9;
	public static Text text_10;
	public static Button btnSomebody_1;
	public static Button button;
	public static Button button_2;
	public static Button button_4;
	public static Button button_5;
	public static Button button_7;
	public static Button button_9;
	public static Button button_11;
	public static Button button_12;
	public static Text text_aver;
	public static Text text_4;

	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			flag_sample = 0;
			shell window = new shell();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();            //key
		createContents();
		shell.open();
		shell.layout();
		//client.reset_res_pool();
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
		shell = new Shell(SWT.CLOSE | SWT.MIN | SWT.TITLE);
		shell.setImage(null);
		shell.setToolTipText("aaaaa");
		shell.setSize(1200, 750);
		shell.setText("SASEP");
		
		//Phy_Res
		composite_phy = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		composite_phy.setBounds(7, 32, 274, 231);		
		composite_phy_con = new Composite(composite_phy, SWT.NONE);
		composite_phy_con.setToolTipText("aaaaaaa");
		composite_phy_con.setSize(500, 5000);
		
		final Label lblTime = new Label(composite_phy_con, SWT.NONE);
		lblTime.setBounds(26, 10, 31, 17);
		lblTime.setText("Time:");
		
		text_time = new Text(composite_phy_con, SWT.BORDER);
		text_time.setText("daytime");
		text_time.setBounds(63, 9, 65, 23);
		
		final Label lblSeason = new Label(composite_phy_con, SWT.NONE);
		lblSeason.setBounds(138, 11, 52, 17);
		lblSeason.setText("Season:");
		
		text_season = new Text(composite_phy_con, SWT.BORDER);
		text_season.setText("Spring");
		text_season.setBounds(190, 8, 65, 23);
		
		final Label lblWeather = new Label(composite_phy_con, SWT.NONE);
		lblWeather.setBounds(7, 52, 55, 17);
		lblWeather.setText("Weather:");
		
		text_weather = new Text(composite_phy_con, SWT.BORDER);
		text_weather.setText("Sunny");
		text_weather.setBounds(63, 49, 52, 23);
		
		btnSomebody_1 = new Button(composite_phy_con, SWT.BORDER);
		btnSomebody_1.setImage(SWTResourceManager.getImage(".\\imagePF\\somebody.jpg"));
		System.out.println(System.getProperty("user.dir"));
		btnSomebody_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (flag_man == 0) {
					btnSomebody_1.setImage(SWTResourceManager.getImage(".\\imagePF\\nobody.jpg"));
					flag_man = 1;
					client.setResValue("somebodyhome", 0);
					if (timerAction != null) {
						timerAction.put_value(null, "somebodyhome", 0);
					}
				} else {
					btnSomebody_1.setImage(SWTResourceManager.getImage(".\\imagePF\\somebody.jpg"));
					flag_man = 0;
					client.setResValue("somebodyhome", 1);
					if (timerAction != null) {
						timerAction.put_value(null, "somebodyhome", 1);
					}
				}
			}
		});
		btnSomebody_1.setBounds(138, 86, 100, 100);
		
		final Label lblSomebody = new Label(composite_phy_con, SWT.NONE);
		lblSomebody.setBounds(137, 55, 75, 17);
		lblSomebody.setText("Somebody:");
		composite_phy.setContent(composite_phy_con);
		//Properties
		composite_pro = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		composite_pro.setBounds(287, 32, 269, 231);		
		composite_pro_con = new Composite(composite_pro, SWT.NONE);
		composite_pro_con.setSize(220, 5000);
		
		final Label lblBrightness = new Label(composite_pro_con, SWT.NONE);
		lblBrightness.setAlignment(SWT.RIGHT);
		lblBrightness.setBounds(24, 10, 84, 17);
		lblBrightness.setText("Brightness:");
		
		final Label lblHumdity = new Label(composite_pro_con, SWT.NONE);
		lblHumdity.setAlignment(SWT.RIGHT);
		lblHumdity.setBounds(47, 45, 61, 17);
		lblHumdity.setText("Humidity:");
		
		final Label lblVolume = new Label(composite_pro_con, SWT.NONE);
		lblVolume.setAlignment(SWT.RIGHT);
		lblVolume.setBounds(47, 79, 61, 17);
		lblVolume.setText("Volume:");
		
		final Label lblTemp = new Label(composite_pro_con, SWT.NONE);
		lblTemp.setAlignment(SWT.RIGHT);
		lblTemp.setBounds(47, 110, 61, 17);
		lblTemp.setText("Temp:");
		
		final Label lblPower = new Label(composite_pro_con, SWT.NONE);
		lblPower.setAlignment(SWT.RIGHT);
		lblPower.setBounds(47, 143, 61, 17);
		lblPower.setText("Power:");
		
		final Label lblAirquality = new Label(composite_pro_con, SWT.NONE);
		lblAirquality.setAlignment(SWT.RIGHT);
		lblAirquality.setBounds(34, 174, 74, 17);
		lblAirquality.setText("Air_Quality:");
		
		text_bri = new Text(composite_pro_con, SWT.BORDER);
		text_bri.setText("1");
		text_bri.setBounds(136, 10, 73, 23);
		
		text_hum = new Text(composite_pro_con, SWT.BORDER);
		text_hum.setText("3");
		text_hum.setBounds(136, 45, 73, 23);
		
		text_vol = new Text(composite_pro_con, SWT.BORDER);
		text_vol.setText("1");
		text_vol.setBounds(136, 79, 73, 23);
		
		text_tem = new Text(composite_pro_con, SWT.BORDER);
		text_tem.setText("3");
		text_tem.setBounds(136, 110, 73, 23);
		
		text_pow = new Text(composite_pro_con, SWT.BORDER);
		text_pow.setText("2");
		text_pow.setBounds(136, 143, 73, 23);
		
		text_air = new Text(composite_pro_con, SWT.BORDER);
		text_air.setText("1");
		text_air.setBounds(136, 174, 73, 23);
		composite_pro.setContent(composite_pro_con);
		//Dev_Res
		composite_dev = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		composite_dev.setBounds(10, 291, 557, 400);		
		composite_dev_con = new Composite(composite_dev, SWT.NONE);
		composite_dev_con.setSize(700, 5000);
		
		final Group grpWindow = new Group(composite_dev_con, SWT.NONE);
		grpWindow.setText("Window");
		grpWindow.setBounds(10, 10, 150, 179);
		
	    button = new Button(grpWindow, SWT.BORDER);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(flag_ChuangLian == 0){
					button.setImage(SWTResourceManager.getImage(".\\imagePF\\guanchl.jpg"));
					flag_ChuangLian = 1;
					txtOn.setText("Off");
					client.setResValue("window",0);
					if (timerAction!=null) {
						timerAction.put_value(null, "window", 0);
					}
				}
				else{
					button.setImage(SWTResourceManager.getImage(".\\imagePF\\kaichl.jpg"));
					flag_ChuangLian = 0;
					txtOn.setText("On");
					client.setResValue("window",1);
					if (timerAction!=null) {
						timerAction.put_value(null, "window", 1);
					}
				}
			}
		});

		button.setImage(SWTResourceManager.getImage(".\\imagePF\\kaichl.jpg"));
		button.setBounds(10, 45, 100, 100);
		
		Label lblState = new Label(grpWindow, SWT.NONE);
		lblState.setBounds(10, 151, 37, 17);
		lblState.setText("State:");
		
		txtOn = new Text(grpWindow, SWT.BORDER);
		txtOn.setText("On");
		txtOn.setBounds(50, 151, 56, 18);
		
		final Group grpBlind = new Group(composite_dev_con, SWT.NONE);
		grpBlind.setText("Blind");
		grpBlind.setBounds(180, 10, 150, 179);
		

		
		button_2 = new Button(grpBlind, SWT.BORDER);
		button_2.setImage(SWTResourceManager.getImage(".\\imagePF\\blindoff.jpg"));
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(flag_blind == 0){
					button_2.setImage(SWTResourceManager.getImage(".\\imagePF\\blindon.jpg"));
					flag_blind = 1;
					txtOff.setText("On");
					client.setResValue("blind",1);
					if (timerAction!=null) {
						timerAction.put_value(null, "blind", 1);
					}
				}
				else{
					button_2.setImage(SWTResourceManager.getImage(".\\imagePF\\blindoff.jpg"));
					flag_blind = 0;
					txtOff.setText("Off");
					client.setResValue("blind",0);
					if (timerAction!=null) {
						timerAction.put_value(null, "blind", 0);
					}
				}
			}
		});
		button_2.setBounds(10, 46, 100, 100);
		
		
		Label label_1 = new Label(grpBlind, SWT.NONE);
		label_1.setText("State:");
		label_1.setBounds(10, 152, 37, 17);
		
		txtOff = new Text(grpBlind, SWT.BORDER);
		txtOff.setText("Off");
		txtOff.setBounds(50, 152, 56, 18);
		
		final Group grpLight = new Group(composite_dev_con, SWT.NONE);
		grpLight.setText("Light");
		grpLight.setBounds(351, 10, 150, 179);
		
		
		button_4 = new Button(grpLight, SWT.BORDER);
		button_4.setImage(SWTResourceManager.getImage(".\\imagePF\\kaideng.jpg"));
		button_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(flag_Light == 0){
					button_4.setImage(SWTResourceManager.getImage(".\\imagePF\\guandeng.jpg"));
					flag_Light = 1;
					text_3.setText("Off");
					client.setResValue("light.power",0);
					if (timerAction!=null) {
						timerAction.put_value(null, "light.power", 0);
					}
				}
				else{
					button_4.setImage(SWTResourceManager.getImage(".\\imagePF\\kaideng.jpg"));
					flag_Light = 0;
					text_3.setText("On");
					client.setResValue("light.power",1);
					if (timerAction!=null) {
						timerAction.put_value(null, "light.power", 1);
					}
				}
			}
		});
		button_4.setBounds(10, 47, 100, 100);
		
		
		Label label_2 = new Label(grpLight, SWT.NONE);
		label_2.setText("State:");
		label_2.setBounds(10, 153, 37, 17);
		
		text_3 = new Text(grpLight, SWT.BORDER);
		text_3.setText("On");
		text_3.setBounds(50, 153, 56, 18);
		
		final Group grpHeater = new Group(composite_dev_con, SWT.NONE);
		grpHeater.setText("Heater");
		grpHeater.setBounds(520, 10, 150, 179);
		
		button_5 = new Button(grpHeater, SWT.NONE);
		button_5.setImage(SWTResourceManager.getImage(".\\imagePF\\heateroff.jpg"));
		button_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(flag_Heater == 0){
					button_5.setImage(SWTResourceManager.getImage(".\\imagePF\\heateron.jpg"));
					flag_Heater = 1;
					txtOff_1.setText("On");
					client.setResValue("heater",1);
					if (timerAction!=null) {
						timerAction.put_value(null, "heater", 1);
					}
				}
				else{
					button_5.setImage(SWTResourceManager.getImage(".\\imagePF\\heateroff.jpg"));
					flag_Heater = 0;
					txtOff_1.setText("Off");
					client.setResValue("heater",0);
					if (timerAction!=null) {
						timerAction.put_value(null, "heater", 0);
					}
				}
			}
		});
		button_5.setBounds(10, 45, 100, 100);

		
		Label label_3 = new Label(grpHeater, SWT.NONE);
		label_3.setText("State:");
		label_3.setBounds(10, 151, 37, 17);
		
		txtOff_1 = new Text(grpHeater, SWT.BORDER);
		txtOff_1.setText("Off");
		txtOff_1.setBounds(50, 151, 56, 18);
		
		final Group grpComputer = new Group(composite_dev_con, SWT.NONE);
		grpComputer.setBounds(9, 202, 150, 214);
		grpComputer.setText("Computer");
		
		button_7 = new Button(grpComputer, SWT.NONE);
		button_7.setImage(SWTResourceManager.getImage(".\\imagePF\\pcclose.jpg"));
		button_7.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(flag_pc == 0){
					button_7.setImage(SWTResourceManager.getImage(".\\imagePF\\pcopen.jpg"));
					flag_pc = 1;
					txtOff_2.setText("On");
					client.setResValue("computer.power",1);
					if (timerAction!=null) {
						timerAction.put_value(null, "computer.power", 1);
					}
				}
				else{
					button_7.setImage(SWTResourceManager.getImage(".\\imagePF\\pcclose.jpg"));
					flag_pc = 0;
					txtOff_2.setText("Off");
					client.setResValue("computer.power",0);
					if (timerAction!=null) {
						timerAction.put_value(null, "computer.power", 0);
					}
				}
			}
		});
		button_7.setBounds(10, 45, 100, 100);
		
		Label label_4 = new Label(grpComputer, SWT.NONE);
		label_4.setAlignment(SWT.RIGHT);
		label_4.setText("State:");
		label_4.setBounds(10, 151, 37, 17);
		
		txtOff_2 = new Text(grpComputer, SWT.BORDER);
		txtOff_2.setText("Off");
		txtOff_2.setBounds(50, 151, 56, 18);
		
		Label lblBri = new Label(grpComputer, SWT.NONE);
		lblBri.setAlignment(SWT.RIGHT);
		lblBri.setBounds(10, 174, 37, 17);
		lblBri.setText("Bri");
		
		text_5 = new Text(grpComputer, SWT.BORDER);
		text_5.setBounds(50, 174, 56, 18);
		text_5.setText("1");
		
		Label lblVol = new Label(grpComputer, SWT.NONE);
		lblVol.setAlignment(SWT.RIGHT);
		lblVol.setBounds(10, 196, 37, 17);
		lblVol.setText("Vol:");
		
		text_6 = new Text(grpComputer, SWT.BORDER);
		text_6.setBounds(50, 195, 56, 18);
		text_6.setText("1");
		
		final Group grpAircon = new Group(composite_dev_con, SWT.NONE);
		grpAircon.setText("AirCon");
		grpAircon.setBounds(180, 202, 150, 214);
		
		button_9 = new Button(grpAircon, SWT.NONE);
		button_9.setImage(SWTResourceManager.getImage(".\\imagePF\\airclose.jpg"));
		button_9.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(flag_air == 0){
					button_9.setImage(SWTResourceManager.getImage(".\\imagePF\\airopen.jpg"));
					flag_air = 1;
					txtOff_3.setText("On");
					client.setResValue("aircondition.power",1);
					if (timerAction!=null) {
						timerAction.put_value(null, "aircondition.power", 1);
					}
				}
				else{
					button_9.setImage(SWTResourceManager.getImage(".\\imagePF\\airclose.jpg"));
					flag_air = 0;
					txtOff_3.setText("Off");
					client.setResValue("aircondition.power",0);
					if (timerAction!=null) {
						timerAction.put_value(null, "aircondition.power", 0);
					}
				}
			}
		});
		button_9.setBounds(10, 45, 100, 100);
		
		Label label_5 = new Label(grpAircon, SWT.NONE);
		label_5.setText("State:");
		label_5.setAlignment(SWT.RIGHT);
		label_5.setBounds(10, 151, 37, 17);
		
		txtOff_3 = new Text(grpAircon, SWT.BORDER);
		txtOff_3.setText("Off");
		txtOff_3.setBounds(50, 151, 56, 18);
		
		Label lblMode = new Label(grpAircon, SWT.NONE);
		lblMode.setBounds(10, 174, 45, 17);
		lblMode.setText("Mode:");
		
		button_11 = new Button(grpAircon, SWT.BORDER);
		button_11.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(flag_tem == 0){
					button_11.setImage(SWTResourceManager.getImage(".\\imagePF\\heat.jpg"));
					flag_tem = 1;
					client.setResValue("aircondition.mode","heat");
					if (timerAction!=null) {
						timerAction.put_value(null, "aircondition.mode", "heat");
					}
				}
				else{
					button_11.setImage(SWTResourceManager.getImage(".\\imagePF\\cool.jpg"));
					flag_tem = 0;
					client.setResValue("aircondition.mode","cool");
					if (timerAction!=null) {
						timerAction.put_value(null, "aircondition.mode", "cool");
					}
				}
			}
		});
		button_11.setImage(SWTResourceManager.getImage(".\\imagePF\\cool.jpg"));
		button_11.setBounds(60, 179, 35, 35);
		
		final Group grpTv = new Group(composite_dev_con, SWT.NONE);
		grpTv.setText("TV");
		grpTv.setBounds(351, 202, 150, 214);
		
		button_12 = new Button(grpTv, SWT.NONE);
		button_12.setImage(SWTResourceManager.getImage(".\\imagePF\\tvoff.jpg"));
		button_12.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(flag_TV == 0){
					button_12.setImage(SWTResourceManager.getImage(".\\imagePF\\tvon.jpg"));
					flag_TV = 1;
					txtOff_4.setText("On");
					client.setResValue("tv.power",1);
					if (timerAction!=null) {
						timerAction.put_value(null, "tv.power", 1);
					}
				}
				else{
					button_12.setImage(SWTResourceManager.getImage(".\\imagePF\\tvoff.jpg"));
					flag_TV = 0;
					txtOff_4.setText("Off");
					client.setResValue("tv.power",0);
					if (timerAction!=null) {
						timerAction.put_value(null, "tv.power", 0);
					}
				}
			}
		});
		button_12.setBounds(10, 45, 100, 100);
		
		Label label_6 = new Label(grpTv, SWT.NONE);
		label_6.setText("State:");
		label_6.setAlignment(SWT.RIGHT);
		label_6.setBounds(10, 151, 37, 17);
		
		txtOff_4 = new Text(grpTv, SWT.BORDER);
		txtOff_4.setText("Off");
		txtOff_4.setBounds(50, 151, 56, 18);
		
		Label label_7 = new Label(grpTv, SWT.NONE);
		label_7.setText("Bri");
		label_7.setAlignment(SWT.RIGHT);
		label_7.setBounds(10, 174, 37, 17);
		
		text_9 = new Text(grpTv, SWT.BORDER);
		text_9.setText("1");
		text_9.setBounds(50, 174, 56, 18);
		
		Label label_8 = new Label(grpTv, SWT.NONE);
		label_8.setText("Vol:");
		label_8.setAlignment(SWT.RIGHT);
		label_8.setBounds(10, 196, 37, 17);
		
		text_10 = new Text(grpTv, SWT.BORDER);
		text_10.setText("1");
		text_10.setBounds(50, 195, 56, 18);
		composite_dev.setContent(composite_dev_con);
		
		
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		MenuItem mntmEnact = new MenuItem(menu, SWT.CASCADE);
		mntmEnact.setText("Enact");
		
		Menu menu_1 = new Menu(mntmEnact);
		mntmEnact.setMenu(menu_1);
		
		MenuItem mntmRes = new MenuItem(menu_1, SWT.NONE);
		mntmRes.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Shell shell = new Shell();
				Form_Phy lf = new Form_Phy(shell,SWT.ALPHA);
				lf.open();
			}
		});
		mntmRes.setSelection(true);
		mntmRes.setText("Create_Resource");
		
		new MenuItem(menu_1, SWT.SEPARATOR);
		lblAddPhyresHere = new Label(composite_phy_con, SWT.NONE);
		lblAddPhyresHere.setBounds(10, 10, 137, 17);
		lblAddPhyresHere.setText("Add Phy_Res Here...");

		lblAddDevresHere = new Label(composite_dev_con, SWT.NONE);
		lblAddDevresHere.setBounds(10, 10, 137, 17);
		lblAddDevresHere.setText("Add Dev_Res Here...");

		lblAddPropertiesHere = new Label(composite_pro_con, SWT.NONE);
		lblAddPropertiesHere.setText("Add Properties Here...");
		lblAddPropertiesHere.setBounds(10, 10, 137, 17);

		final Label lblOut = new Label(composite_phy_con, SWT.NONE);
		lblOut.setText("Out_Tem:");
		lblOut.setBounds(7, 97, 55, 17);

		text_4 = new Text(composite_phy_con, SWT.BORDER);
		text_4.setText("3");
		text_4.setBounds(63, 94, 52, 23);
		//Sample
		grpAircon.setVisible(false);
		grpBlind.setVisible(false);
		grpComputer.setVisible(false);
		grpHeater.setVisible(false);
		grpLight.setVisible(false);
		grpTv.setVisible(false);
		grpWindow.setVisible(false);

		lblTime.setVisible(false);
		lblSeason.setVisible(false);
		text_season.setVisible(false);
		text_time.setVisible(false);
		lblWeather.setVisible(false);
		text_weather.setVisible(false);
		lblOut.setVisible(false);
		text_4.setVisible(false);
		lblSomebody.setVisible(false);
		btnSomebody_1.setVisible(false);

		lblAirquality.setVisible(false);
		lblBrightness.setVisible(false);
		lblHumdity.setVisible(false);
		lblPower.setVisible(false);
		lblTemp.setVisible(false);
		lblVolume.setVisible(false);
		text_air.setVisible(false);
		text_bri.setVisible(false);
		text_hum.setVisible(false);
		text_pow.setVisible(false);
		text_tem.setVisible(false);
		text_vol.setVisible(false);

		lblAddPhyresHere.setVisible(true);
		lblAddDevresHere.setVisible(true);
		lblAddPropertiesHere.setVisible(true);
		lblAddPhyresHere.setVisible(true);
		lblAddDevresHere.setVisible(true);
		lblAddPropertiesHere.setVisible(true);

		//~Sample

		MenuItem mntmSample = new MenuItem(menu_1, SWT.CHECK);
		mntmSample.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(flag_sample == 1)
				{
					flag_sample=0;
					lblAddPhyresHere.setVisible(true);
					lblAddDevresHere.setVisible(true);
					lblAddPropertiesHere.setVisible(true);
					lblTime.setVisible(false);
					lblWeather.setVisible(false);
					lblSeason.setVisible(false);
					lblSomebody.setVisible(false);
					text_weather.setVisible(false);
					text_season.setVisible(false);
					text_time.setVisible(false);
					btnSomebody_1.setVisible(false);
					lblOut.setVisible(false);
					text_4.setVisible(false);

					lblAirquality.setVisible(false);
					lblBrightness.setVisible(false);
					lblHumdity.setVisible(false);
					lblPower.setVisible(false);
					lblTemp.setVisible(false);
					lblVolume.setVisible(false);
					text_air.setVisible(false);
					text_bri.setVisible(false);
					text_hum.setVisible(false);
					text_pow.setVisible(false);
					text_tem.setVisible(false);
					text_vol.setVisible(false);
					grpAircon.setVisible(false);
					grpBlind.setVisible(false);
					grpComputer.setVisible(false);
					grpHeater.setVisible(false);
					grpLight.setVisible(false);
					grpTv.setVisible(false);
					grpWindow.setVisible(false);
				}
				else
				{
					flag_sample=1;
					lblAddPhyresHere.setVisible(false);
					lblAddDevresHere.setVisible(false);
					lblAddPropertiesHere.setVisible(false);

					lblTime.setVisible(true);
					lblWeather.setVisible(true);
					lblSeason.setVisible(true);
					lblSomebody.setVisible(true);
					text_weather.setVisible(true);
					text_season.setVisible(true);
					text_time.setVisible(true);
					btnSomebody_1.setVisible(true);
					lblOut.setVisible(true);
					text_4.setVisible(true);

					lblAirquality.setVisible(true);
					lblBrightness.setVisible(true);
					lblHumdity.setVisible(true);
					lblPower.setVisible(true);
					lblTemp.setVisible(true);
					lblVolume.setVisible(true);
					text_air.setVisible(true);
					text_bri.setVisible(true);
					text_hum.setVisible(true);
					text_pow.setVisible(true);
					text_tem.setVisible(true);
					text_vol.setVisible(true);
					grpAircon.setVisible(true);
					grpBlind.setVisible(true);
					grpComputer.setVisible(true);
					grpHeater.setVisible(true);
					grpLight.setVisible(true);
					grpTv.setVisible(true);
					grpWindow.setVisible(true);
				}
			}
		});
		mntmSample.setText("Sample");
		
		MenuItem mntmConfig = new MenuItem(menu, SWT.CASCADE);
		mntmConfig.setText("Config");
		
		Menu menu_2 = new Menu(mntmConfig);
		mntmConfig.setMenu(menu_2);
		
		MenuItem mntmInitrespool = new MenuItem(menu_2, SWT.NONE);
		mntmInitrespool.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Shell shell = new Shell();
				Form_Specification lf = new Form_Specification(shell,SWT.ALPHA);
				lf.open();
			}
		});
		mntmInitrespool.setText("Load_Envronment_Specification");
		
		new MenuItem(menu_2, SWT.SEPARATOR);
		
		MenuItem mntmLoadinitfile = new MenuItem(menu_2, SWT.NONE);
		mntmLoadinitfile.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Shell shell = new Shell();
				Form_Specification lf = new Form_Specification(shell,SWT.ALPHA);
				lf.open();
			}
		});
		mntmLoadinitfile.setText("Load_Rule_Set");
		
		new MenuItem(menu_2, SWT.SEPARATOR);
		
		MenuItem mntmLoadgoalspecification = new MenuItem(menu_2, SWT.NONE);
		mntmLoadgoalspecification.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Shell shell = new Shell();
				Form_Specification lf = new Form_Specification(shell,SWT.ALPHA);
				lf.open();
			}
		});
		mntmLoadgoalspecification.setText("Load_Goal_Specification");
		
		MenuItem mntmReadMe = new MenuItem(menu, SWT.CASCADE);
		mntmReadMe.setText("Help");
		
		Menu menu_3 = new Menu(mntmReadMe);
		mntmReadMe.setMenu(menu_3);
		
		MenuItem mntmAbout = new MenuItem(menu_3, SWT.NONE);
		mntmAbout.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageBox box = new MessageBox(shell, SWT.ICON_INFORMATION);
				box.setMessage("Auth. : ACT Team, PKU");
				box.open();
			}
		});
		mntmAbout.setText("About");
		
		new MenuItem(menu_3, SWT.SEPARATOR);
		
		MenuItem mntmReadme = new MenuItem(menu_3, SWT.NONE);
		mntmReadme.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageBox box = new MessageBox(shell, SWT.ICON_INFORMATION);
				box.setMessage("SASEP(Self-Adaptation system Simulation and Evaluation Platform) is a visualization tool which helps users construct self-adaptive software systems, then simulates and evaluates \nthe running of them, according to specifications.");
				box.open();
			}
		});
		mntmReadme.setText("Read_me");
		
		Label label = new Label(shell, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(573, 0, 2, 701);
		
		//chart
		Composite composite = new Composite(shell, SWT.BORDER);
		composite.setBounds(581, 10, 593, 275);
		
		chart = new Chart(composite,SWT.NONE);
		chart.setBounds(0, 19, 579, 251);
		
		Label lblGoalsatisfidegree = new Label(composite, SWT.NONE);
		lblGoalsatisfidegree.setBounds(10, 0, 152, 17);
		lblGoalsatisfidegree.setText("Goal_Satisfaction_Degree");
		
		Label lblAverage = new Label(composite, SWT.NONE);
		lblAverage.setBounds(439, 0, 61, 17);
		lblAverage.setText("Average:");
		
		text_aver = new Text(composite, SWT.BORDER);
		text_aver.setBounds(506, 0, 73, 17);
		// set titles
		chart.getTitle().setText("goal");
		chart.getAxisSet().getXAxis(0).getTitle().setText("Clock");
		chart.getAxisSet().getYAxis(0).getTitle().setText("Satisfaction");
		// create line series
		ILineSeries lineSeries = (ILineSeries) chart.getSeriesSet()
		    .createSeries(ISeries.SeriesType.LINE, "line series");

		lineSeries.setYSeries(ySeries);

		
		Group grpActiverules = new Group(shell, SWT.NONE);
		grpActiverules.setText("Active_Rules");
		grpActiverules.setBounds(580, 291, 594, 154);
		
		text = new Text(grpActiverules, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
		text.setBounds(10, 23, 574, 121);
		
		final Group grpSystemclock = new Group(shell, SWT.NONE);
		grpSystemclock.setText("System_Control");
		grpSystemclock.setBounds(932, 451, 242, 240);
		
		text_1 = new Text(grpSystemclock, SWT.BORDER);
		text_1.setBounds(103, 26, 73, 23);
		
		Label lblNewLabel = new Label(grpSystemclock, SWT.NONE);
		lblNewLabel.setBounds(27, 29, 61, 17);
		lblNewLabel.setText("Clock");
		
		Label lblStep = new Label(grpSystemclock, SWT.NONE);
		lblStep.setBounds(27, 58, 61, 17);
		lblStep.setText("Step");
		
		text_2 = new Text(grpSystemclock, SWT.BORDER);
		text_2.setText("15000");
		text_2.setBounds(103, 55, 73, 23);
		
		Button btnNewButton = new Button(grpSystemclock, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				clock_step = Integer.parseInt(text_2.getText());
			}
		});
		btnNewButton.setBounds(207, 54, 26, 27);
		btnNewButton.setText("√");
		
		Button btnStartclock = new Button(grpSystemclock, SWT.NONE);
		btnStartclock.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				startClock(null);
			}
		});
		btnStartclock.setBounds(27, 87, 90, 27);
		btnStartclock.setText("Start_Clock");
		
		Button btnStopclock = new Button(grpSystemclock, SWT.NONE);
		btnStopclock.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				stopClock();
			}
		});
		btnStopclock.setBounds(135, 87, 90, 27);
		btnStopclock.setText("Stop_Clock");
		
		Button btnRestartclock = new Button(grpSystemclock, SWT.NONE);
		btnRestartclock.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				startClock(null);
			}
		});
		btnRestartclock.setBounds(27, 120, 90, 27);
		btnRestartclock.setText("Restart_Clock");
		
		Button btnTicktock = new Button(grpSystemclock, SWT.NONE);
		btnTicktock.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				startClock(clock+1);
			}
		});
		btnTicktock.setBounds(135, 120, 90, 27);
		btnTicktock.setText("Tick_Tock");
		
		Label lblMs = new Label(grpSystemclock, SWT.NONE);
		lblMs.setBounds(182, 59, 18, 18);
		lblMs.setText("ms");
		
		Button btnStartrespool = new Button(grpSystemclock, SWT.NONE);
		btnStartrespool.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						ProcessBuilder pb = new ProcessBuilder()
								.directory(new File("..\\AeroAnt\\ResPool"))
								.command(new String[]{"python", "res_pool.py"});
						pb.redirectErrorStream(true);
						try {
							Process process = pb.start();
							BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
							String line;
							while((line = br.readLine())!=null) {System.out.println(line);

							}
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}).start();
			}
		});
		btnStartrespool.setBounds(27, 170, 90, 27);
		btnStartrespool.setText("Start_ResPool");
		
		Button btnStartagent = new Button(grpSystemclock, SWT.NONE);
		btnStartagent.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						ProcessBuilder pb = new ProcessBuilder()
								.directory(new File("..\\AeroAnt"))
								.command(new String[]{"python", "test_agent.py"});
						pb.redirectErrorStream(true);
						try {
							Process process = pb.start();
							BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
							String line;
							while((line = br.readLine())!=null) {System.out.println(line);

								//System.out.flush();
							}
							//System.out.print("output:" + process.exitValue());

						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}).start();
			}
		});
		btnStartagent.setBounds(135, 170, 90, 27);
		btnStartagent.setText("Start_Agent");
		
		Button btnInitres = new Button(grpSystemclock, SWT.NONE);
		btnInitres.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						ProcessBuilder pb = new ProcessBuilder()
								.directory(new File("..\\AeroAnt"))
								.command(new String[]{"python", "init_res.py"});
						pb.redirectErrorStream(true);
						try {
							Process process = pb.start();
							BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
							String line;
							while((line = br.readLine())!=null) {System.out.println(line);
							}

						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}).start();
			}
		});
		btnInitres.setBounds(135, 203, 90, 27);
		btnInitres.setText("Init_Res");
		
		Button btnResetrespool = new Button(grpSystemclock, SWT.NONE);
		btnResetrespool.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				client.reset_res_pool();
				MessageBox box = new MessageBox(shell, SWT.ICON_INFORMATION);
				box.setMessage("Reset ResPool Done!");
				box.open();
			}
		});
		btnResetrespool.setBounds(27, 203, 90, 27);
		btnResetrespool.setText("Reset_ResPool");
		
		Group grpUser = new Group(shell, SWT.NONE);
		grpUser.setText("User_Preference");
		grpUser.setBounds(581, 451, 345, 240);
		//table
		table = new Table(grpUser, SWT.BORDER | SWT.FULL_SELECTION | SWT.HIDE_SELECTION | SWT.VIRTUAL);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(10, 31, 294, 113);

		TableColumn tblclmnGoal = new TableColumn(table, SWT.CENTER);
		tblclmnGoal.setWidth(145);
		tblclmnGoal.setText("    Goal");

		TableColumn tblclmnWeight = new TableColumn(table, SWT.CENTER);
		tblclmnWeight.setWidth(146);
		tblclmnWeight.setText("Weight");

		TableItem tableItem_2 = new TableItem(table, SWT.NONE);
		tableItem_2.setText(0,"energy saving");
		tableItem_2.setText(1,"0.25");

		TableItem tableItem = new TableItem(table, SWT.NONE);
		tableItem.setText(new String[]{"effective control","0.25"});

		TableItem tableItem_1 = new TableItem(table, SWT.NONE);
		tableItem_1.setText(new String[]{"visual comfort","0.25"});

		TableItem tableItem_3 = new TableItem(table, SWT.NONE);
		tableItem_3.setText(new String[]{"thermal comfort","0.25"});

		TableCursor tableCursor = new TableCursor(table, SWT.NONE);
		TableItem[] items = table.getItems();
		for (int i = 0; i < items.length; i++) {
			
			final TableEditor editor = new TableEditor(table);
			final Text text = new Text(table, SWT.NONE);
			text.setText(items[i].getText(1));
			editor.grabHorizontal = true;
			editor.setEditor(text, items[i],1);
			text.addModifyListener(new ModifyListener()
			{
				public void modifyText(ModifyEvent e)
				{
					editor.getItem().setText(1, text.getText());
				}

			});
		}
		
		
		Label lblGoalStaf = new Label(grpUser, SWT.NONE);
		lblGoalStaf.setBounds(10, 164, 152, 17);
		lblGoalStaf.setText("Goal Satisfaction Degree");
		
		combo_2 = new Combo(grpUser, SWT.NONE);
		combo_2.setItems(new String[] {"goal_overall", "goal_effective_control", "goal_visual_comfort", "goal_thermal_comfort", "goal_energy"});
		combo_2.setToolTipText("");
		combo_2.setBounds(10, 187, 100, 25);
		combo_2.setText("goal_overall");
		
		//show graph
		Button btnShowGraph = new Button(grpUser, SWT.NONE);
		btnShowGraph.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//text_1.setText("!!!");
				Shell shellx = new Shell();
				GoalChart gc = new GoalChart(shellx, SWT.ALPHA);
				//mocker mock = new mocker();
				//List<Double> data = (List<Double>) mock.getResValue("temp");
				String s = combo_2.getText();

				s = "room:" + s;
				List<Double> data = null;
				if (s != null) {
					data = client.getResValue(s, Double[].class);
				}
				if (data == null) {
					mocker mock = new mocker();
					data = (List<Double>) mock.getResValue("temp");
				}
				double[] chart_y = new double[data.size()];
				int count = 0;
				for (Double x : data) {
					chart_y[count] = x.doubleValue();
					count++;
				}

				GoalChart.ySeries = chart_y;
				System.out.println("\n");
				gc.open();
			}
		});
		btnShowGraph.setBounds(213, 185, 89, 27);
		btnShowGraph.setText("Show Graph");
		
		
		Label lblPhyres = new Label(shell, SWT.NONE);
		lblPhyres.setBounds(10, 9, 61, 17);
		lblPhyres.setText("Phy_Res");
		
		Label lblProperties = new Label(shell, SWT.NONE);
		lblProperties.setBounds(291, 9, 61, 17);
		lblProperties.setText("Properties");
		
		Label lblDevres = new Label(shell, SWT.NONE);
		lblDevres.setBounds(12, 269, 61, 17);
		lblDevres.setText("Dev_Res");

	}
	
	private void stopClock() {
		if (timer == null) {
			return;
		}
		timer.cancel();
		timer.purge();
		timer = null;
		timerAction.isStop = true;
	}

	private void startClock(Integer targetClock) {
		if (timer != null) {
			stopClock();
		}
		timer = new java.util.Timer();
		timerAction = new  TimerAction(targetClock);
		timer.scheduleAtFixedRate(timerAction, 1000, 1000);
		timerAction.thread.start();
	}
}
