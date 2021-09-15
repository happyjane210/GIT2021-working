interface AlertProp {
  message: string;
  variant: string;
  onClose?: () => void;
}

const Alert = ({ message, variant, onClose }: AlertProp) => {
  return (
    <div
      className={`alert alert-${variant} alert-dismissible my-2`}
      role="alert"
    >
      {message}
      <button
        type="button"
        className="btn-close"
        data-bs-dismiss="alert"
        aria-label="Close"
        onClick={onClose}
      ></button>
    </div>
  );
};

export default Alert;
