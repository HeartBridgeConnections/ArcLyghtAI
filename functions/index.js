import { onRequest } from "firebase-functions/v2/https";
import * as logger from "firebase-functions/logger";
import "dotenv/config";
import OpenAI from "openai";
import mongoose from "mongoose";
import fetch from "node-fetch";

// 🔐 Setup OpenAI
const openai = new OpenAI({
  apiKey: process.env.OPENAI_API_KEY
});

// 🌐 Optional: Connect to MongoDB (if needed)
mongoose.connect(process.env.MONGO_URI || "")
  .then(() => logger.info("MongoDB connected"))
  .catch(err => logger.error("MongoDB connection error:", err));

// 🧠 Verify reCAPTCHA (v3)
async function verifyRecaptcha(token) {
  const secretKey = process.env.RECAPTCHA_SECRET_KEY;
  const url = `https://www.google.com/recaptcha/api/siteverify`;

  const response = await fetch(url, {
    method: 'POST',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    body: `secret=${secretKey}&response=${token}`
  });

  const data = await response.json();
  return data.success && data.score > 0.5;
}

// 🌟 Main Cloud Function
export const helloArcLyght = onRequest(async (req, res) => {
  try {
    const prompt = req.body.prompt || "Say something!";
    const recaptchaToken = req.body.recaptchaToken;

    // ✅ reCAPTCHA Validation
    const isHuman = await verifyRecaptcha(recaptchaToken);
    if (!isHuman) {
      logger.warn("reCAPTCHA verification failed.");
      return res.status(403).send({ error: "reCAPTCHA failed" });
    }

    // ✅ OpenAI Call
    const response = await openai.chat.completions.create({
      model: "gpt-4",
      messages: [{ role: "user", content: prompt }]
    });

    const reply = response.choices[0].message.content;
    logger.info("OpenAI response", reply);

    return res.status(200).send({ reply });

  } catch (error) {
    logger.error("Error in helloArcLyght:", error);
    return res.status(500).send({ error: error.message });
  }
});
