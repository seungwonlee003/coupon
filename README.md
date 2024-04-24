# Scenario

A ride-sharing platform has decided to issue 10,000 coupons for a 100% discount. In conjunction with this event, they aim to develop a universal coupon system as a microservice. The coupon system must meet the following requirements:

## Non-functional Specification
- Issuance will be conducted on a first-come, first-served basis.
- The expected request load for the event (coupon issuance) is a maximum of 1500 requests per second.
- The system should be capable of handling request loads that are deemed difficult to process on a single server.
- There is no need to implement scaling out. However, the system should be designed in a way that it can handle deployment across multiple servers without issues.

## Functional Specification
### Coupons
- Each coupon must have a name and identifier.
- Coupons can be issued in a fixed quantity or infinitely. (Hereinafter referred to as "Quantity")
- Each coupon must have its own start date. (Hereinafter referred to as "Start Date")
- Each coupon must have its own expiration date. (Hereinafter referred to as "Expiration Date")
- Coupons must be used within N days after issuance. (Hereinafter referred to as "Validity Period after Issuance")
- The expiration date is determined as the shorter of the validity period after issuance expiration date and the coupon's own expiration date.
- Coupons must contain either a discount rate or an absolute discount amount. (Hereinafter referred to as "Discount Information")
- Each coupon is associated with a user and is used independently per account. (Hereinafter referred to as "User Information")
- User information is a unique string.
- The issuance timestamp must be retrievable.
- It must be possible to determine whether a coupon has already been used. (Hereinafter referred to as "Usage Status Information")
- It must be possible to determine which product the coupon was used for.
- Product information is a unique string.
- The timestamp of usage must be retrievable.

### Reading All Coupons
- Retrieve all coupons’ info and its stock count.
- Support pagination.

### Coupon Creation
- Create a coupon based on the provided information.

### Coupon Modification
- Modify a specific coupon identified by certain information with the provided modifications.
- Modifying the validity period after issuance does not affect existing coupons.
- For already issued coupons, user information and usage status information cannot be changed.

### Deleting a coupon
- Upon receiving the coupon ID, delete the coupon.

### Coupon Issuance
- Issue a specific coupon by receiving user identification information and the coupon information to be issued.
- If duplicate issuance is attempted, the issuance should fail.
- If the user already possesses an expired coupon, it is not considered a duplicate issuance.
- If all coupon quantities have been exhausted, the issuance should fail.

### Reading User's Coupons
- Provide a list of coupons issued to a specific user.
- Do not show expired coupons.
- Send all information related to each coupon.
- Support pagination.

### Coupon Usage by User
- Receive coupon identification information held by the user to update the usage status information.
- Incorrect requests should result in usage failure.
- Usage should fail if the validity period has expired.

### Cancelling Coupon Usage by User
- Receive coupon identification information held by the user to update the usage status information.

### Deleting User's Coupon
- Upon receiving specific user ID and coupon ID, delete the coupon issued to the user.
