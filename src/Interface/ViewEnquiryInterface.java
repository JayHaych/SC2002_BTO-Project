package Interface;

import Entity.User;

/**
 * The {@code ViewEnquiryInterface} interface defines a contract for classes that allow users
 * to view their enquiries. This is typically implemented by user role-specific controllers such
 * as {@code ApplicantController} or others handling enquiry-related operations.
 */
public interface ViewEnquiryInterface {

    /**
     * Displays the list of enquiries made by the specified user.
     *
     * @param user The user whose enquiries are to be viewed.
     */
    void viewEnquiry(User user);
}
