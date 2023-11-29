package hu.uni.miskolc.mobilprogramozas2023fosz.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CimDAO {
    @Insert
    void insert(Cim cim);

    @Query("Select * from Cim")
    List<Cim> getAllCim();

    @Query("Select * from Cim Where iranyitoszam= :iranyitoszam")
    List<Cim> getAllCimByIranyitoszam(String iranyitoszam);
    @Delete
    void delete(Cim cim);

    @Update
    void update(Cim cim);

    @Query("Select * from Cim Where iranyitoszam= :iranyitoszam and varos= :varos and utca = :utca and hazszam = :hazszam")
    List<Cim> findSame(String iranyitoszam, String varos, String utca, String hazszam);

    default List<Cim> findSame(Cim cim){
        return findSame(cim.getIranyitoszam(), cim.getVaros(), cim.getUtca(), cim.getHazszam());
    }
}
