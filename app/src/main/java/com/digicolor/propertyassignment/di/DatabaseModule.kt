package com.digicolor.propertyassignment.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.digicolor.propertyassignment.data.dao.CategoriesDao
import com.digicolor.propertyassignment.data.dao.GroceryDao
import com.digicolor.propertyassignment.data.databse.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Volatile
    private var INSTANCE: AppDataBase? = null

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDataBase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context,
                AppDataBase::class.java,
                "app_db"
            )
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
                            INSTANCE?.getCategoryDao()?.insertAll(DefaultCategories.list)
                        }
                    }
                })
                .build()
            INSTANCE = instance
            instance
        }
    }

    @Provides
    fun providesCategoriesDao(database: AppDataBase): CategoriesDao {
        return database.getCategoryDao()
    }

    @Provides
    fun providesGroceryDao(database: AppDataBase): GroceryDao {
        return database.groceryDao()
    }
}