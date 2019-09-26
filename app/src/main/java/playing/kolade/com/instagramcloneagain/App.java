package playing.kolade.com.instagramcloneagain;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("nD2XMuh5cvJcNZj35L6Sjfe6JfZFi4Ha7fyDLTR3")
                // if defined
                .clientKey("yj5qDgf0LWEzT71cMIkRXCJXMBxJyWJjujvatnwI")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
