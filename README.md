EmailService - Microservice for Sending Emails

EmailService is a microservice that consumes email messages from a Kafka topic (send_email) and uses the Google API to send these emails to the intended recipients.

📦 Features

Consumes email messages from a Kafka topic (send_email).

Parses and processes the email messages.

Integrates with Google API to send emails.

Handles retries and error logging.

🛠️ Technologies Used

Java Spring Boot - Core framework for the microservice.

Apache Kafka - Messaging service to consume email data.

Google API - To send emails using Google services.

Maven - Dependency management and build tool.
