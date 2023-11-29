package hu.uni.miskolc.mobilprogramozas2023fosz.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Cim.class}, version = 1)
public abstract class CimDatabase extends RoomDatabase {
    public abstract CimDAO getCimDao();
}
