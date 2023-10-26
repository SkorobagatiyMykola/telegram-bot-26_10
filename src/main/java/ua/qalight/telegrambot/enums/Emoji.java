package ua.qalight.telegrambot.enums;

public enum Emoji {
    SMILE(":smile:"),
    BLUSH(":blush:"),
    WINK_EYE(":stuck_out_tongue_winking_eye:");

    private String emoji;

    Emoji(String emoji) {
        this.emoji = emoji;
    }

    public String getEmoji() {
        return emoji;
    }
}
