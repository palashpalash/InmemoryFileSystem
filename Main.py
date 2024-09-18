

import cv2
import os
import numpy as np

# Path to your dataset (faces images in separate folders)
data_path = "/Users/pdas/desktop/My Photo"

# Initialize face recognizer (LBPH) and face detector (Haar Cascades)
recognizer = cv2.face.LBPHFaceRecognizer_create()
face_cascade = cv2.CascadeClassifier(cv2.data.haarcascades + 'haarcascade_frontalface_default.xml')


# Function to read images and corresponding labels
def get_images_and_labels(data_path):
    face_samples = []
    ids = []

    # Traverse through folders
    for folder_name in os.listdir(data_path):
        folder_path = os.path.join(data_path, folder_name)
        if not os.path.isdir(folder_path):
            continue

        # Label is the folder name (assuming each folder contains images of one person)
        label = (int)(folder_name)

        # Traverse through each image in the folder
        for image_name in os.listdir(folder_path):
            image_path = os.path.join(folder_path, image_name)
            gray_image = cv2.imread(image_path, cv2.IMREAD_GRAYSCALE)

            # Detect faces in the image
            faces = face_cascade.detectMultiScale(gray_image)

            for (x, y, w, h) in faces:
                face_samples.append(gray_image[y:y + h, x:x + w])
                ids.append(label)

    return face_samples, ids


# Training the face recognizer
def train_recognizer():
    # Loading the images and labels for training
    faces, ids = get_images_and_labels(data_path)

    # Train the model
    recognizer.train(faces, np.array(ids))

    # Save the trained model to a file
    recognizer.write('trained_model.yml')
    print("Model trained and saved successfully.")


# Face recognition function
def recognize_face(image_path):
    # Load the pre-trained model
    recognizer.read('trained_model.yml')

    # Read the image for recognition
    img = cv2.imread(image_path)
    gray_img = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)

    # Detect faces in the image
    faces = face_cascade.detectMultiScale(gray_img, scaleFactor=1.2, minNeighbors=5)

    for (x, y, w, h) in faces:
        # Extract the face region
        face = gray_img[y:y + h, x:x + w]

        # Predict the label using the recognizer
        label, confidence = recognizer.predict(face)

        # Print the label and confidence
        print(f"Label: {label}, Confidence: {confidence}")

        # Draw a rectangle around the face and label it
        cv2.rectangle(img, (x, y), (x + w, y + h), (255, 0, 0), 2)
        cv2.putText(img, f"ID: {label}", (x, y - 10), cv2.FONT_HERSHEY_SIMPLEX, 0.9, (255, 0, 0), 2)

    # Show the image with recognized faces
    cv2.imshow('Face Recognition', img)
    cv2.waitKey(0)
    cv2.destroyAllWindows()


# Main function
if __name__ == "__main__":
    # First, train the model
    train_recognizer()

    # Then, recognize faces in a new image
    test_image_path = '/Users/pdas/Desktop/b.jpeg'  # Path to the image for recognition
    recognize_face(test_image_path)