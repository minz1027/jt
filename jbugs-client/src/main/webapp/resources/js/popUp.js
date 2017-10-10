function handlePopUpRequest(id, xhr, status, args) {
	if (args.validationFailed) {
		PF(id).jq.effect("shake", {
			times : 5
		}, 100);
	} else {
		PF(id).hide();
	}
}