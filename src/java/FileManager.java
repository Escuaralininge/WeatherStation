
package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;

public class FileManager {

	private File file;

	public FileManager () {}

	public FileManager (String path) {
		setFile(path);
	}

	public void setFile (String path) {
		this.file = new File(path);
	}

	public ArrayList<String> readFile () {
		ArrayList<String> arrayList = new ArrayList<String>();
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				arrayList.add(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public void writeFile (ArrayList<String> arrayList) {
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
			for (String string: arrayList) {
				byte charArr [] = string.getBytes();
				for (byte i: charArr) {
					bufferedWriter.write(i);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> grep (ArrayList<String> al, String p) {
		ArrayList<String> fal = new ArrayList<String>();
		for (String s: al) {
			if (s.contains(p)) {
				fal.add(s);
			}
		}
		return fal;
	}

}
