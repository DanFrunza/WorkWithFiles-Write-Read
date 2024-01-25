import java.awt.*;
import java.io.*;

class TaskList extends Frame{
	String path, dir;
	TextArea textArea = new TextArea();
	
	
	
	public static void main(String args[]){new TaskList();}
	
	TaskList(){
		Dimension res=getToolkit().getScreenSize();
		setBackground(new Color(38,104,165));
		setForeground(new Color(255,255,0));
		setResizable(false);
		adaugaMenuBar();
		setTitle("Task list");
		resize(700,500);
		
		show();
		
	}
	
	void adaugaMenuBar(){
		MenuBar men=new MenuBar();
		Menu f=new Menu("File");
		f.add("Open TaskList");
		f.add("New TaskList");
		f.add("Save Tasklist");
		f.add("Exit");
		men.add(f);
		setMenuBar(men);
		
	}
	
	public boolean handleEvent(Event e){
		if(e.id==Event.WINDOW_DESTROY){
			System.exit(0);
		}else if(e.id==Event.ACTION_EVENT && e.target instanceof MenuItem){
			if("Exit".equals(e.arg)){
				System.exit(0);
			}else if("Open TaskList".equals(e.arg)){
				loadFile();
				return true;
			}else if("New TaskList".equals(e.arg)){
				System.out.println("gfdgfd");
				CreateFile();
				return true;
			}else if("Save Tasklist".equals(e.arg)){
				SaveFile();
				return true;
			}
		}else return false;
		return super.handleEvent(e);
		
	}
	
	void loadFile(){
		System.out.println("S a apelat loadFile");
		try{
			FileDialog fd=new FileDialog(this, "Open File", 0);
			if(dir!=null) fd.setDirectory(dir);
			fd.setVisible(true);
			if(fd.getFile()!=null){
				dir=fd.getDirectory();
				String fisier=fd.getFile();
				path=dir+fisier;
				Afiseaza(path);
			}
			
		}catch(Exception e){e.printStackTrace();}
	}
	
	void Afiseaza(String FilePath){
		try(BufferedReader br=new BufferedReader(new FileReader(FilePath))){;
		
		String line;
		while((line=br.readLine())!=null){
			textArea.append(line + "\n");
		}
		
		textArea.setFont(new Font("Arial", Font.PLAIN, 20)); 
        textArea.setForeground(Color.BLACK); 
		
		add(textArea, BorderLayout.CENTER);
		validate();
		
		}catch(IOException e){e.printStackTrace();}
		
	}
	
	void CreateFile(){
		System.out.println("S a apelat create");
		FileDialog fd = new FileDialog(this, "Save File", 0);
		fd.setVisible(true);
		
		if(fd.getFile()!=null){
			dir=fd.getDirectory();
			String fisier=fd.getFile();
			path=dir+fisier;
			
			if(!path.toLowerCase().endsWith(".txt")){
				path+=".txt";
			}
			
			try(BufferedWriter writer= new BufferedWriter(new FileWriter(path))){
				textArea.setText("");
				Afiseaza(path);
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	void SaveFile(){
		if(path!=null){
			try(BufferedWriter writer = new BufferedWriter(new FileWriter(path))){
				writer.write(textArea.getText());
				
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
}