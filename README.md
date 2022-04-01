
# Chitra

Chitra is a library which has capablity to give you multiple images from storage. It is look like a gallery which has folders of images with foldername. If you click on particular folder you can see images of that particular folder. You can select or unselect images even you can zoom each and every images. We provided preview portion where you can see all selected images even you can remove images from that preview portion. The main benifit of this library is you can select multiple images from multiple folders and view each selected images in preview portion and you can remove it. Even programmer can set limit of image selection. If you want only ten images you can set limit here. Even you don't want to select same image and If you want to keep old images selected, then you can do that also.

# Use

Here we have provided some code so you can understand better.

### 1. Get Images

#### Here you can get multiple images.

```http
  Bildbekommen bildbekommen;
  private Button btn_first;

  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        bildbekommen = new Bildbekommen();
        btn_first = findViewById(R.id.btn_first);

        btn_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bildbekommen.execute(MainActivity.this);
            }
        });
    }

```
### 2. Get Images with limit

#### Here you can get multiple images even you can set image selection limit.

```http
  Bildbekommen bildbekommen;
  private Button btn_second;

  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        bildbekommen = new Bildbekommen();
        btn_second = findViewById(R.id.btn_second);

        btn_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bildbekommen.setLimit(3).execute(MainActivity.this);
            }
        });
    }

```
### 3. Get Images with keep seleted images

#### Here you can get multiple images even If you want to keep old images selected here you can do it.

```http
  Bildbekommen bildbekommen;
  private Button btn_thired;
  private ArrayList<String> list, old_selected_images_list;

  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        bildbekommen = new Bildbekommen();
        btn_thired = findViewById(R.id.btn_thired);
        list = new ArrayList<>();
        old_selected_images_list = new ArrayList<>();

        list = old_selected_images_list;

        btn_thired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bildbekommen.setList(list).execute(MainActivity.this);
            }
        });
    }

```
### 4. Get Images , set limit with keep seleted images

#### Here you can get multiple images even you can set image selection limit end If you want to keep old images selected here you can do it.

```http
  Bildbekommen bildbekommen;
  private Button btn_fourth;
  private ArrayList<String> list, old_selected_images_list;

  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        bildbekommen = new Bildbekommen();
        btn_fourth = findViewById(R.id.btn_fourth);
        list = new ArrayList<>();
        old_selected_images_list = new ArrayList<>();

        list = old_selected_images_list;

        btn_fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bildbekommen.setList(list).setLimit(3).execute(MainActivity.this);
            }
        });
    }

```
# Releases

#### Put this code in build.gradle (project)

```http
  allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}
  
```
#### Put this code in build.gradle (app)

```http
  dependencies {
    implementation 'com.github.J-Neutron:Chitra:1.0.0'
  }
  
```



