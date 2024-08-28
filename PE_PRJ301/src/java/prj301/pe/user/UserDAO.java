/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prj301.pe.user;

import com.sun.org.apache.bcel.internal.generic.PUTFIELD;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import prj301.pe.utils.DBUtils;

/**
 *
 * @author Hoadnt
 */
public class UserDAO implements Serializable {
//    your code here

    public UserDTO CheckLogin(String userID, String password) throws SQLException, ClassNotFoundException {
        // tạo connection 
        Connection con = null;
        //tạo preparedStatement 
        PreparedStatement stm = null;
        //tạo resultSet
        ResultSet rs = null;
        //tạo ra kết quả trả ra theo kiểu ở trên 
        UserDTO result = null;
        try {

            //1. Connect to Database 
            con = DBUtils.getConnection();
            //2. Create SQL String 
            if (con != null) {
                String sql = "Select fullName,roleID,status "
                        + "from tblUsers "
                        + "where userID = ? "
                        + "and password = ?";
                //3. tạo PreparedStatement 
                stm = con.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setString(2, password);

                //4. execute Query
                rs = stm.executeQuery();
                //5. Xử lí kết quả  
                if (rs.next()) {
                    //map 
                    //get
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    boolean status = rs.getBoolean("status");
                    //set 
                    result = new UserDTO(userID, fullName, "", roleID, status);
                }
            }

        } finally {
            // đóng lại ResultSet
            if (rs != null) {
                rs.close();
            }
            // đóng stm 
            if (stm != null) {
                stm.close();
            }
            //đóng con 
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    private List<UserDTO> ListUserdto;

    public List<UserDTO> getListUserdto() {
        return ListUserdto;
    }

    public void Search(String searchString) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            // kết nối với db 
            con = DBUtils.getConnection();
            if (con != null) {
                // create SQL String 
                String sql = "Select userID, fullName, roleID, status "
                        + "from tblUsers "
                        + "where fullName like ? "
                        + "and status = 1";
                // tạo statement object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%"+searchString+"%");
                // execute query 
                rs = stm.executeQuery();
                //process result 
                while (rs.next()) {// sài while vì thứ hiển thị > 1 
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    UserDTO result = new UserDTO(userID, fullName, "", roleID, true);
                    if (this.ListUserdto == null) {
                        // nếu chưa có thì tạo 
                        this.ListUserdto = new ArrayList<>();
                    }
                    this.ListUserdto.add(result);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

}
