package edu.kytsmen.java.io.db;

import javax.swing.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.*;

/**
 * Created by dkytsmen on 10/26/16.
 */
public class DerbyDataLoader {
    public static void main(String[] args) {

    }

    public void loadImage() throws Exception {
        Connection con;
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/home/dkytsmen/Pictures");

        PreparedStatement ps;
        ps = con.prepareStatement("insert into employee(name,photo) " + "values(?,?)");
        ps.setString(1, "Duke");

        Blob blob = con.createBlob();
        ImageIcon ii = new ImageIcon("duke.png");

        ObjectOutputStream oos;
        oos = new ObjectOutputStream(blob.setBinaryStream(1));
        oos.writeObject(ii);
        oos.close();
        ps.setBlob(2, blob);
        ps.execute();
        blob.free();
        ps.close();
    }

    public void readImage() throws Exception {
        ImageIcon image;

        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/home/dkytsmen/Pictures");

        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("select photo from employee where name = 'Duke'");
        if (rs.next()) {
            Blob photo = rs.getBlob(1);
            ObjectInputStream ois = null;
            ois = new ObjectInputStream(photo.getBinaryStream());
            image = (ImageIcon) ois.readObject();
        }
        s.close();
    }
}
