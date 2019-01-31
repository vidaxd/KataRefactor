package es.ulpgc;

public class Video {

    private final String title;
    private enum Rating {TWELVE, FIFTEEN, EIGHTEEN}
    private final Rating rating;

    public Video(String title, Rating rating) {
        this.title = title;
        this.rating = rating;
    }

    public Rating getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public void rentFor(Customer customer) throws CustomerUnderAgeException {
        if(isUnderAge(customer)) throw new CustomerUnderAgeException();
        customer.addRental(this);
    }

    private boolean isUnderAge(Customer customer) {

            // calculate customer's age in years and months
            int age = getCustomerAge(customer);

            // determine if customer is under legal age for rating
            if (rating == Rating.TWELVE) {
                return age < 12;
            } else if (rating == Rating.FIFTEEN) {
                return age < 15;
            } else if (rating == Rating.EIGHTEEN) {
                return age < 18;
            }

        return false;

    }

    private int getCustomerAge(Customer customer) {

        try {

            // parse customer date of birth
            Calendar calDateOfBirth = Calendar.getInstance();
            calDateOfBirth.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(customer.getDateOfBirth()));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        // get current date
        Calendar calNow = Calendar.getInstance();
        calNow.setTime(new java.util.Date());

        // calculate age different in years and months
        int ageYr = (calNow.get(Calendar.YEAR) - calDateOfBirth.get(Calendar.YEAR));
        int ageMo = (calNow.get(Calendar.MONTH) - calDateOfBirth.get(Calendar.MONTH));

        // decrement age in years if month difference is negative
        if (ageMo < 0) ageYr--;
        return ageYr;
    }

}