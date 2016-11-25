package nl.fressh.nederlandviertfeest;

import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;



@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 24)
public class TrialTestAppTest {
        private MainActivity activity;

        @Before
        public void setup() {
            // Convenience method to run MainActivity through the Activity Lifecycle methods:
            // onCreate(...) => onStart() => onPostCreate(...) => onResume()
            activity = Robolectric.setupActivity(MainActivity.class);
        }

        @Test
        public void checkJUnitWork() {
            // failing test gives much better feedback
            // to show that all works correctly ;)
            assertThat(true, );
        }

}