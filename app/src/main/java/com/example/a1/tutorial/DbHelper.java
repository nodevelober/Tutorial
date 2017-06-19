package com.example.a1.tutorial;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by a1 on 01.03.17.
 */

public class DbHelper extends SQLiteOpenHelper{

    public static final int DATA_VERSION = 1;
    public static final String DATABSE_NAME ="THeoryTut";
    public static final String TABLE_QUESTION = "Questions";
    public static final String TABLE_THEORY ="Theory";
    public static final String TABLE_DISCRIPTION = "Discriptions";
    public static final String TABLE_ANSWERS = "Answers";
    public static final String TABLE_STAT = "Statistic";
    public static final String TABLE_USERS = "Users";

    public static final String KEY_ID = "_id";
    public static final String KEY_UserID = "UserID";
    public static final String KEY_POINTS = "Points";
    public static final String KEY_NAME = "UserName";
    public static final String KEY_INFO ="info";
    public static final String KEY_DISCRIPTION = "Discription";
    public static final String KEY_QUESTION = "Question";
    public static final String KEY_ANSWER = "Answer";
    public static final String KEY_NUM_QUESTION = "numAnswer";
    public static final String KEY_FLAG = "flag";
    public static final String KEY_PASS = "Password";

    public DbHelper(Context context) {
        super(context, DATABSE_NAME, null, DATA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_THEORY+ " (" + KEY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_INFO
                + " TEXT);");

        db.execSQL("CREATE TABLE " + TABLE_DISCRIPTION + " (" + KEY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_DISCRIPTION + " TEXT);");

        db.execSQL("CREATE TABLE " + TABLE_QUESTION +" (" + KEY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_QUESTION + " TEXT);");

        db.execSQL("CREATE TABLE " + TABLE_ANSWERS +" (" + KEY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_ANSWER + " TEXT," + KEY_NUM_QUESTION +" INTEGER,"+ KEY_FLAG + " INTEGER);");

        db.execSQL("CREATE TABLE " + TABLE_STAT +" (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_UserID + " INTEGER," + KEY_POINTS + " INTEGER);");

        db.execSQL("CREATE TABLE " + TABLE_USERS +" (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME + " TEXT, "+ KEY_PASS +" TEXT);");

        db.execSQL("INSERT INTO " + TABLE_USERS + "(" + KEY_NAME +"," + KEY_PASS + ") VALUES ('User','12345');");

        db.execSQL("INSERT INTO " + TABLE_THEORY + "(" + KEY_INFO + ") VALUES ('Общее понятие');");
        db.execSQL("INSERT INTO " + TABLE_THEORY + "(" + KEY_INFO + ") VALUES ('Оперативная память');");
        db.execSQL("INSERT INTO " + TABLE_THEORY + "(" + KEY_INFO + ") VALUES ('Предназначение оперативной памяти');");
        db.execSQL("INSERT INTO " + TABLE_THEORY + "(" + KEY_INFO + ") VALUES ('Постоянная память');");
        db.execSQL("INSERT INTO " + TABLE_THEORY + "(" + KEY_INFO + ") VALUES ('CD и DVD-диски');");
        db.execSQL("INSERT INTO " + TABLE_THEORY + "(" + KEY_INFO + ") VALUES ('Накопители SSD');");



        /* Теория */
        String discrip1= "'Память компьютера — специальное устройство для записи и хранения различного рода данных. Выделяют два типа памяти в компьютерном устройстве: оперативная и постоянная (внутренняя и внешняя).'";
        String discrip2= "'Оперативная память — быстрый тип памяти, позволяющий с высокой скоростью записывать и считывать данные, но при этом информация хранится в ней только во включенном состоянии компьютерного устройства, то есть когда на нее подается электричество. Именно этот нюанс делает оперативную память непригодной для долгосрочного хранения информации. Выключите компьютер — и вся информация из оперативной памяти будет стерта.'";
        String discrip3= "'Предназначение оперативной памяти — это запись-чтение информации с высокой скоростью установленными программами и операционной системой. Загрузка компьютера при включении представляет собой всего лишь загрузку необходимых для работы программ в оперативную память. Оперативная память бывает нескольких типов: SDRAM, DDR, DDR2, DDR3. Каждый последующий тип памяти представляет собой улучшение предыдущего и позволяет новой памяти работать с большей скоростью. В данный момент в современных компьютерах используется оперативная память типа DDR3. Выбор оперативной памяти зависит от разъемов на материнской плате.'";
        String discrip4= "'Постоянная память — тип памяти, позволяющий хранить информацию и при выключенном компьютере. Наиболее распространенный вариант постоянной памяти — жесткие диски HDD. Они представляют собой один или несколько магнитных дисков, вращающихся с огромной скоростью (от 5 до 12 тысяч оборотов в минуту), и головок, предназначенных для считывания и записи информации. HDD являются надежными носителями информации, позволяют записывать и считывать информацию огромное количество раз. Единственный их минус — они очень восприимчивы к ударам, падениям и прочим механическим воздействиям, особенно в момент работы.\n'";
        String discrip6= "'Все большее распространение набирают твердотельные накопители SSD. Данный вид постоянной памяти развился из USB-флеш-накопителей. Основные преимущества и недостатки SSD-накопителей:\n" +
                "имеют в разы более высокую скорость чтения и записи, чем HDD;\n" +
                "не восприимчивы к механическим воздействиям;\n" +
                "стоимость SSD-накопителей превышает плату за HDD в несколько раз;\n" +
                "имеют конечное количество циклов чтения-записи.'";
        String discrip5= "'CD и DVD-диски также относятся к постоянной памяти компьютера, являясь относительно недорогим вариантом хранения небольших объемов информации. Опасность потери информации на этих носителях состоит в их механическом повреждении: царапины, разломы, термическое воздействие. Каждый вид памяти компьютерного устройства имеет свои преимущества и недостатки, но есть некоторые, без которых компьютер не будет работать. CD и DVD-диски, USB-флеш-накопитель, съемный жесткий диск являются необязательными комплектующими в системном блоке, а без оперативной памяти и локального жесткого диска устройство не будет функционировать.'";

        db.execSQL("INSERT INTO " + TABLE_DISCRIPTION + "(" + KEY_DISCRIPTION + ") VALUES (" + discrip1 + ");");
        db.execSQL("INSERT INTO " + TABLE_DISCRIPTION + "(" + KEY_DISCRIPTION + ") VALUES (" + discrip2 + ");");
        db.execSQL("INSERT INTO " + TABLE_DISCRIPTION + "(" + KEY_DISCRIPTION + ") VALUES (" + discrip3 + ");");
        db.execSQL("INSERT INTO " + TABLE_DISCRIPTION + "(" + KEY_DISCRIPTION + ") VALUES (" + discrip4 + ");");
        db.execSQL("INSERT INTO " + TABLE_DISCRIPTION + "(" + KEY_DISCRIPTION + ") VALUES (" + discrip5 + ");");
        db.execSQL("INSERT INTO " + TABLE_DISCRIPTION + "(" + KEY_DISCRIPTION + ") VALUES (" + discrip6 + ");");



        /* Вопросы */
        String question1 ="'Какие типы памяти бывают?' ";
        String question2 ="'Специальное устройство для записи и хранения различного рода данных это?' ";
        String question3 ="'К какой памяти компьютера относятся CD и DVD-диски?' ";
        String question4 ="'Из чего развились SSD накопители?' ";
        String question5 ="'Вопрос 5' ";
        String question6 ="'Вопрсо 6' ";

        db.execSQL("INSERT INTO " + TABLE_QUESTION + "(" + KEY_QUESTION + ") VALUES (" + question1 + ");");
        db.execSQL("INSERT INTO " + TABLE_QUESTION + "(" + KEY_QUESTION + ") VALUES (" + question2 + ");");
        db.execSQL("INSERT INTO " + TABLE_QUESTION + "(" + KEY_QUESTION + ") VALUES (" + question3 + ");");
        db.execSQL("INSERT INTO " + TABLE_QUESTION + "(" + KEY_QUESTION + ") VALUES (" + question4 + ");");
        db.execSQL("INSERT INTO " + TABLE_QUESTION + "(" + KEY_QUESTION + ") VALUES (" + question5 + ");");
        db.execSQL("INSERT INTO " + TABLE_QUESTION + "(" + KEY_QUESTION + ") VALUES (" + question6 + ");");


        /* Ответы */
        String answer11 = "'Временная' ";
        String answer12 = "'Оперативная и постоянная' ";
        String answer13 = "'Оперативная' ";
        String answer14 = "'Внешняя' ";
        String answer21 = "'Оперативная память' ";
        String answer22 = "'Память компьютера' ";
        String answer23 = "'Постоянна память' ";
        String answer24 = "'SSD диск' ";
        String answer31 = "'Временной' ";
        String answer32 = "'Постоянной' ";
        String answer33 = "'Оперативной' ";
        String answer34 = "'Внешней' ";
        String answer41 = "'Оперативная память' ";
        String answer42 = "'HDD' ";
        String answer43 = "'USB-флеш накопители' ";
        String answer44 = "'CD или DVD-диски' ";

        db.execSQL("INSERT INTO " + TABLE_ANSWERS + "(" + KEY_ANSWER + ", " + KEY_NUM_QUESTION + ", " + KEY_FLAG + ") VALUES (" + answer11 + ", 1, 1);");
        db.execSQL("INSERT INTO " + TABLE_ANSWERS + "(" + KEY_ANSWER + ", " + KEY_NUM_QUESTION + ", " + KEY_FLAG + ") VALUES (" + answer12 + ", 1, 0);");
        db.execSQL("INSERT INTO " + TABLE_ANSWERS + "(" + KEY_ANSWER + ", " + KEY_NUM_QUESTION + ", " + KEY_FLAG + ") VALUES (" + answer13 + ", 1, 0);");
        db.execSQL("INSERT INTO " + TABLE_ANSWERS + "(" + KEY_ANSWER + ", " + KEY_NUM_QUESTION + ", " + KEY_FLAG + ") VALUES (" + answer14 + ", 1, 0);");
        db.execSQL("INSERT INTO " + TABLE_ANSWERS + "(" + KEY_ANSWER + ", " + KEY_NUM_QUESTION + ", " + KEY_FLAG + ") VALUES (" + answer21 + ", 2, 0);");
        db.execSQL("INSERT INTO " + TABLE_ANSWERS + "(" + KEY_ANSWER + ", " + KEY_NUM_QUESTION + ", " + KEY_FLAG + ") VALUES (" + answer22 + ", 2, 2);");
        db.execSQL("INSERT INTO " + TABLE_ANSWERS + "(" + KEY_ANSWER + ", " + KEY_NUM_QUESTION + ", " + KEY_FLAG + ") VALUES (" + answer23 + ", 2, 0);");
        db.execSQL("INSERT INTO " + TABLE_ANSWERS + "(" + KEY_ANSWER + ", " + KEY_NUM_QUESTION + ", " + KEY_FLAG + ") VALUES (" + answer24 + ", 2, 0);");
        db.execSQL("INSERT INTO " + TABLE_ANSWERS + "(" + KEY_ANSWER + ", " + KEY_NUM_QUESTION + ", " + KEY_FLAG + ") VALUES (" + answer31 + ", 3, 0);");
        db.execSQL("INSERT INTO " + TABLE_ANSWERS + "(" + KEY_ANSWER + ", " + KEY_NUM_QUESTION + ", " + KEY_FLAG + ") VALUES (" + answer32 + ", 3, 2);");
        db.execSQL("INSERT INTO " + TABLE_ANSWERS + "(" + KEY_ANSWER + ", " + KEY_NUM_QUESTION + ", " + KEY_FLAG + ") VALUES (" + answer33 + ", 3, 0);");
        db.execSQL("INSERT INTO " + TABLE_ANSWERS + "(" + KEY_ANSWER + ", " + KEY_NUM_QUESTION + ", " + KEY_FLAG + ") VALUES (" + answer34 + ", 3, 0);");
        db.execSQL("INSERT INTO " + TABLE_ANSWERS + "(" + KEY_ANSWER + ", " + KEY_NUM_QUESTION + ", " + KEY_FLAG + ") VALUES (" + answer41 + ", 4, 0);");
        db.execSQL("INSERT INTO " + TABLE_ANSWERS + "(" + KEY_ANSWER + ", " + KEY_NUM_QUESTION + ", " + KEY_FLAG + ") VALUES (" + answer42 + ", 4, 0);");
        db.execSQL("INSERT INTO " + TABLE_ANSWERS + "(" + KEY_ANSWER + ", " + KEY_NUM_QUESTION + ", " + KEY_FLAG + ") VALUES (" + answer43 + ", 4, 3);");
        db.execSQL("INSERT INTO " + TABLE_ANSWERS + "(" + KEY_ANSWER + ", " + KEY_NUM_QUESTION + ", " + KEY_FLAG + ") VALUES (" + answer44 + ", 4, 0);");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_THEORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DISCRIPTION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANSWERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STAT);


        onCreate(db);
    }


}
