/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import business.Genre;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public interface GenreDaoInterface {
    
    public boolean checkIfGenreExists(String genre);
    
    public boolean addGenre(Genre genre);
    
    public ArrayList<Genre> showAllGenres();
    
}
