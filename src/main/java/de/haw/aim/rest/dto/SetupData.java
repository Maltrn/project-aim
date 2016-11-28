package de.haw.aim.rest.dto;

import de.haw.aim.authentication.persistence.User;
import de.haw.aim.authentication.persistence.UserRepository;
import de.haw.aim.uploadcenter.persistence.*;
import de.haw.aim.vendor.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SetupData {

    private ProductInfoRepository productInfoRepository;

    private VendorInfoRepository vendorInfoRepository;

    private VendorRepository vendorRepository;

    private PictureRepository pictureRepository;

    private PDFRepository pdfRepository;

    private UserRepository userRepository;

    public SetupData(ProductInfoRepository productInfoRepository, VendorInfoRepository vendorInfoRepository, VendorRepository vendorRepository, PictureRepository pictureRepository, UserRepository userRepository, PDFRepository pdfRepository) {
        this.productInfoRepository = productInfoRepository;
        this.vendorInfoRepository = vendorInfoRepository;
        this.vendorRepository = vendorRepository;
        this.pictureRepository = pictureRepository;
        this.userRepository = userRepository;
        this.pdfRepository = pdfRepository;
    }

    public void setup() {

        pictureRepository.deleteAll();
        userRepository.deleteAll();
        vendorInfoRepository.deleteAll();
        productInfoRepository.deleteAll();
        vendorRepository.deleteAll();
        pdfRepository.deleteAll();

        List<UploadedFile> vendorUploadedFiles = new ArrayList<>();

        Picture vendorMainPicture = pictureRepository.save(new Picture("vendor_mainpic.jpg"));
        vendorUploadedFiles.add(vendorMainPicture);
        Picture product1MainPicture = pictureRepository.save(new Picture("product1_mainpic.jpg"));
        vendorUploadedFiles.add(product1MainPicture);
        Picture product2MainPicture = pictureRepository.save(new Picture("product2_mainpic.jpg"));
        vendorUploadedFiles.add(product2MainPicture);

        Picture vendorPicture = pictureRepository.save(new Picture("vendor_pic.jpg"));
        PDF vendorPdf = pdfRepository.save(new PDF("vendor_doc.png"));
        List<UploadedFile> vendorFileGallery = new ArrayList<>();
        vendorFileGallery.add(vendorPicture);
        vendorUploadedFiles.add(vendorMainPicture);
        vendorFileGallery.add(vendorPdf);
        vendorUploadedFiles.add(vendorPdf);

        Picture product1Picture = pictureRepository.save(new Picture("product1_pic.jpg"));
        PDF product1Pdf = pdfRepository.save(new PDF("product1_doc.png"));
        List<UploadedFile> product1FileGallery = new ArrayList<>();
        product1FileGallery.add(product1Picture);
        vendorUploadedFiles.add(product1Picture);
        product1FileGallery.add(product1Pdf);
        vendorUploadedFiles.add(product1Pdf);

        Picture product2Picture = pictureRepository.save(new Picture("product2_pic.jpg"));
        PDF product2Pdf = pdfRepository.save(new PDF("product2_doc.png"));
        List<UploadedFile> product2FileGallery = new ArrayList<>();
        product1FileGallery.add(product2Picture);
        vendorUploadedFiles.add(product2Picture);
        product1FileGallery.add(product2Pdf);
        vendorUploadedFiles.add(product2Pdf);

        List<Fact> vendorFacts = new ArrayList<>();
        Fact vendorFact1 = new Fact("price", "we are cheap!");
        Fact vendorFact2 = new Fact("magic", "magic included!");
        vendorFacts.add(vendorFact1);
        vendorFacts.add(vendorFact2);

        List<Fact> product1Facts = new ArrayList<>();
        Fact product1fact1 = new Fact("first fact", "This fact ist beautiful!!");
        Fact product1fact2 = new Fact("Roses are red", "DUP DUP DUP");
        product1Facts.add(vendorFact1);
        product1Facts.add(vendorFact2);

        List<Fact> product2Facts = new ArrayList<>();
        Fact product2fact1 = new Fact("diameter", "Holy Moly - this cable is perfect for you!");
        Fact product2fact2 = new Fact("length", "You won't see the end coming");
        product2Facts.add(product2fact1);
        product2Facts.add(product2fact2);

        VendorInfo vendorInfo = vendorInfoRepository.save(new VendorInfo("Lovely Phones", "Look at their phones, their phones are amazing.", "Give it a lick", vendorMainPicture, vendorFileGallery, vendorFacts));
        ProductInfo productInfo1 = productInfoRepository.save(new ProductInfo("phone", "ring ring ring Banana Phone", "You can dial and press some keys, it's worth it", product1MainPicture, product1FileGallery, product1Facts));
        ProductInfo productInfo2 = productInfoRepository.save(new ProductInfo("cable", "it great and long", "experience a great cable", product2MainPicture, product2FileGallery, product2Facts));

        List<ProductInfo> productInfos = new ArrayList<>();
        productInfos.add(productInfo1);
        productInfos.add(productInfo2);

        User user = userRepository.save(new User("john", "pony"));
        //user.setCurrentToken("handsomeTOKEN");
        List<User> users = new ArrayList<>();
        users.add(user);

        Vendor vendor = vendorRepository.save(new Vendor(vendorInfo, productInfos, users, vendorUploadedFiles));

    }

}
