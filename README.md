# SemantiStack Simple

This is a simplified version of SemantiStack for easy understanding.

## Structure
semantistack-simple/
 ├── backend-java/  (Java OOP backend)
 ├── ai-python/     (Python AI service with BERT)
 ├── frontend/      (HTML, CSS, JS)

## Run Instructions

1. Start Python AI service:
   ```bash
   cd ai-python
   pip install -r requirements.txt
   python ai_service.py
   ```

2. Start Java backend (simple HTTP server):
   ```bash
   cd backend-java
   javac SimpleServer.java
   java SimpleServer
   ```

3. Open frontend/index.html in browser and test search.
