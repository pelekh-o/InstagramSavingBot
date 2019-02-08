package instaUtil;

import com.opelekh.instaUtil.InstagramUtil;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class InstagramUtilTest {
    private static String postOnePhoto, postTwoPhotos, postThreePhotos, postNinePhotos;
    private static String zeroLenStr;

    private InstagramUtil instagramUtil;
    private List imgUrls;

    @BeforeClass
    public static void init() {
        postOnePhoto = "https://www.instagram.com/p/BtluyofHvbi/?__a=1";
        postTwoPhotos = "https://www.instagram.com/p/BtlilZ7HAwK/?__a=1";
        postThreePhotos = "https://www.instagram.com/p/BtTtSOvl4RP/?__a=1";
        postNinePhotos = "https://www.instagram.com/p/BtbHofHBsvf/?__a=1";

        zeroLenStr = "";
    }

    @Before
    public void beforeEach() {
        instagramUtil = new InstagramUtil();
    }

    @Test(expected = Exception.class)
    public void getImagesUrls() {
        imgUrls = instagramUtil.getImagesUrls(postOnePhoto);
        assertEquals(1, imgUrls.size());

        imgUrls = instagramUtil.getImagesUrls(postTwoPhotos);
        assertEquals(2, imgUrls.size());

        imgUrls = instagramUtil.getImagesUrls(postThreePhotos);
        assertEquals(3, imgUrls.size());

        imgUrls = instagramUtil.getImagesUrls(postNinePhotos);
        assertEquals(9, imgUrls.size());

        imgUrls = instagramUtil.getImagesUrls(zeroLenStr);
        assertNull(imgUrls);

        assertNull(instagramUtil.getImagesUrls(null));

        instagramUtil.getImagesUrls("https://www.instagram.com/p//");
    }

    @Test
    public void readJson() {
        imgUrls = instagramUtil.getImagesUrls(postOnePhoto);
        assertNotNull(imgUrls);
    }
}