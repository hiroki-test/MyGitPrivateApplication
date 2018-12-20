package com.lib.mygitapp.mygitlibrary;

import android.content.Context;
import android.text.TextUtils;

import wseemann.media.FFmpegMediaMetadataRetriever;

public class MediaMetadataRetrieverWrapper {

	private FFmpegMediaMetadataRetriever ffmpegMmr;
	protected String mPath;

	public MediaMetadataRetrieverWrapper(Context context) {
		ffmpegMmr = new FFmpegMediaMetadataRetriever();
	}

	public void setDataSource(String path) {
		mPath = path;
		ffmpegMmr.setDataSource(path);
	}

	public String getAudioCodec() {
		return ffmpegMmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_AUDIO_CODEC);
	}

	public String getTitle() {
		if (getAudioCodec().equals(RetrieverConst.AUDIO_CODEC_FLAC)) {
			return ffmpegMmr.extractMetadata(RetrieverConst.METADATA_KEY_TITLE_FLAC);
		} else if (getAudioCodec().equals(RetrieverConst.AUDIO_CODEC_AAC)) {
			return ffmpegMmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_TITLE);
		}
		return null;
	}

	public String getTitleKana() {
		if (getAudioCodec().equals(RetrieverConst.AUDIO_CODEC_FLAC)) {
			return ffmpegMmr.extractMetadata(RetrieverConst.METADATA_KEY_TITLE_KANA_FLAC);
		} else if (getAudioCodec().equals(RetrieverConst.AUDIO_CODEC_AAC)) {
			return ffmpegMmr.extractMetadata(RetrieverConst.METADATA_KEY_TITLE_KANA_AAC);
		}
		return null;
	}

	public String getAlbum() {
		if (getAudioCodec().equals(RetrieverConst.AUDIO_CODEC_FLAC)) {
			return ffmpegMmr.extractMetadata(RetrieverConst.METADATA_KEY_ALBUM_FLAC);
		} else if (getAudioCodec().equals(RetrieverConst.AUDIO_CODEC_AAC)) {
			return ffmpegMmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_ALBUM);
		}
		return null;
	}

	public String getAlbumKana() {
		if (getAudioCodec().equals(RetrieverConst.AUDIO_CODEC_FLAC)) {
			return ffmpegMmr.extractMetadata(RetrieverConst.METADATA_KEY_ALBUM_KANA_FLAC);
		} else if (getAudioCodec().equals(RetrieverConst.AUDIO_CODEC_AAC)) {
			return ffmpegMmr.extractMetadata(RetrieverConst.METADATA_KEY_ALBUM_KANA_AAC);
		}
		return null;
	}

	public String getAlbumArtist() {
		return ffmpegMmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_ALBUM_ARTIST);
	}

	public String getAlbumArtistKana() {
		if (getAudioCodec().equals(RetrieverConst.AUDIO_CODEC_FLAC)) {
			return ffmpegMmr.extractMetadata(RetrieverConst.METADATA_KEY_ALBUMARTIST_KANA_FLAC);
		} else if (getAudioCodec().equals(RetrieverConst.AUDIO_CODEC_AAC)) {
			return ffmpegMmr.extractMetadata(RetrieverConst.METADATA_KEY_ALBUMARTIST_KANA_AAC);
		}
		return null;
	}

	public String getArtist() {
		if (getAudioCodec().equals(RetrieverConst.AUDIO_CODEC_FLAC)) {
			return ffmpegMmr.extractMetadata(RetrieverConst.METADATA_KEY_ARTIST_FLAC);
		} else if (getAudioCodec().equals(RetrieverConst.AUDIO_CODEC_AAC)) {
			return ffmpegMmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_ARTIST);
		}
		return null;
	}

	public String getArtistKana() {
		if (getAudioCodec().equals(RetrieverConst.AUDIO_CODEC_FLAC)) {
			return ffmpegMmr.extractMetadata(RetrieverConst.METADATA_KEY_ARTIST_KANA_FLAC);
		} else if (getAudioCodec().equals(RetrieverConst.AUDIO_CODEC_AAC)) {
			return ffmpegMmr.extractMetadata(RetrieverConst.METADATA_KEY_ARTIST_KANA_AAC);
		}
		return null;
	}

	public int getDiskNumber() {
		String disk = ffmpegMmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_DISC);
		return extractCurrentTrackNumber(disk);
	}

	public int getTrackNumber() {
		String track = ffmpegMmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_TRACK);
		return extractCurrentTrackNumber(track);
	}

	public byte[] getAlbumArt() {
		return ffmpegMmr.getEmbeddedPicture();
	}

	public void release() {
		if (ffmpegMmr != null) {
			ffmpegMmr.release();
		}
	}

	protected int toInt(String str) {
		return TextUtils.isEmpty(str) ? 0 : Integer.parseInt(str);
	}

	protected int extractCurrentTrackNumber(String number) {
		if (number != null) {
			String[] trackArr = number.split("/");
			return toInt(trackArr[0].trim());
		}
		return 0;
	}
}
