package com.opelekh.instaUtil;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class InstagramUtilTest {
    private static String postOnePhoto, postTwoPhotos, postThreePhotos, postNinePhotos;
    private List imgUrls;

    @BeforeClass
    public static void init() {
        postOnePhoto = "https://www.instagram.com/p/BtqYwyrhk-K/?utm_source=ig_web_copy_link&";
        postTwoPhotos = "https://www.instagram.com/p/BtlilZ7HAwK/?utm_source=ig_share_sheet&igshid=1kjcjdlaa6hi5";
        postThreePhotos = "https://www.instagram.com/p/BtTtSOvl4RP/?utm_source=ig_web_copy_link";
        postNinePhotos = "https://www.instagram.com/p/BtbHofHBsvf/?__a=1";
    }

    @Test(expected = Exception.class)
    public void getImagesUrls() {
        imgUrls = new InstagramUtil(postOnePhoto).getImagesUrls();
        assertEquals(1, imgUrls.size());

        imgUrls = new InstagramUtil(postTwoPhotos).getImagesUrls();
        assertEquals(2, imgUrls.size());

        imgUrls = new InstagramUtil(postThreePhotos).getImagesUrls();
        assertEquals(3, imgUrls.size());

        imgUrls = new InstagramUtil(postNinePhotos).getImagesUrls();
        assertEquals(9, imgUrls.size());

        imgUrls = new InstagramUtil("").getImagesUrls();
        assertNull(imgUrls);

        imgUrls = new InstagramUtil("https://www.instagram.com/p//").getImagesUrls();
        assertNull(imgUrls);

        imgUrls = new InstagramUtil(null).getImagesUrls();
    }

    @Test
    public void readJson() {
        try {
            assertNotNull(new InstagramUtil(postOnePhoto).readJson());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getHashtags() {
        String hashtags = new InstagramUtil(postThreePhotos).getHashtags();
        assertEquals(115, hashtags.length());

        hashtags = new InstagramUtil(postOnePhoto).getHashtags();
        assertEquals(4, hashtags.length());
    }
}